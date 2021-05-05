package com.jumanji.capston.service;

import com.jumanji.capston.data.*;
import com.jumanji.capston.repository.ReviewRepository;
import com.jumanji.capston.service.exception.reviewException.ReviewHasExistException;
import com.jumanji.capston.service.exception.reviewException.ReviewNotFoundException;
import com.jumanji.capston.service.interfaces.BasicService;
import com.jumanji.capston.service.interfaces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService, BasicService {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    StorageServiceImpl storageService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ShopServiceImpl shopService;
    @Autowired
    OrderServiceImpl orderService;


    @Override
    public Review get(String reviewId) {
        return (Review) isPresent(reviewId);
    }

    @Override
    public List<Review> getList(String shopId) {
        return reviewRepository.findAllByShopIdOrderByRegTimeDesc(shopId);
    }

    @Override
    public Review post(String authorization, Review.Request request) {
        System.out.println("리뷰등록's shopId : " + request.getShopId());

        String loginId = userService.getMyId(authorization);
        String uri = "shop/" + request.getShopId() + "/review/"; // TODO 얘를 storage service에서 만들어 주는 메소드를 만들어야 할듯.
        String imgPath = "";
        Timestamp orderId = new Timestamp(Long.parseLong(request.getOrderId()));


        // 유효성 검사
        User user = userService.isPresent(loginId); // 유저 유효성 검사.
        Shop shop = shopService.isPresent(request.getShopId()); //
        Order order = orderService.isPresent(orderId);
        isEmpty(request.getShopId()); // 해당 사업자 번호로 사업자 등록이 됐는지 확인 비어있어야 등록

        if (request.getImg() != null)
            imgPath = storageService.store(request.getImg(), request.getImg().getName(), uri.split("/"));
        Date regDate = new Date();
        Timestamp regTime = new Timestamp(regDate.getTime());
        Review review;
        String reviewId = request.getShopId().substring(0,2) + DateOperator.dateToYYYYMMDD(regDate);
        System.out.println("개수 가져오기 전 reivewId : " + reviewId);
        int countDayReviews = reviewRepository.countByIdStartingWith(reviewId);
        System.out.println("해당 날짜 리뷰 개수 : " + countDayReviews);
        StringUtils.append(reviewId, String.valueOf(countDayReviews));
        System.out.println("개수 붙인 후 reivewId : " + reviewId);
        review = Review.init()
                .id(reviewId)
                .content(request.getContent())
                .regTime(regTime)
                .parentId(request.getParentId())
                .score(request.getScore())
                .imgUrl(imgPath)
                .user(user)
                .shop(shop)
                .order(order)
                .build();
        review = reviewRepository.save(review);
        return review;
    }

    @Override
    public Review patch(String authorization, Review.Request request) {
        return null;
    }

    @Override
    public void delete(String authorization, String reviewId) {

    }

    @Override
    public Object isPresent(String reviewId) {
        Optional<Review> review = reviewRepository.findById(reviewId);
        if(review.isPresent())return review.get();
        throw new ReviewNotFoundException();
    }

    @Override
    public boolean isEmpty(String reviewId) {
        Optional<Review> review = reviewRepository.findById(reviewId);
        if(review.isEmpty())return true;
        throw new ReviewHasExistException();
    }
}
