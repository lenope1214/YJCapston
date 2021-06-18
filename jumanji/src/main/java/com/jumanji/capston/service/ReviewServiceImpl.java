package com.jumanji.capston.service;

import com.jumanji.capston.data.*;
import com.jumanji.capston.repository.OrderRepository;
import com.jumanji.capston.repository.ReviewRepository;
import com.jumanji.capston.service.exception.orderException.OrderNotPaidException;
import com.jumanji.capston.service.exception.reviewException.ReviewHasExistException;
import com.jumanji.capston.service.exception.reviewException.ReviewIsNotYoursException;
import com.jumanji.capston.service.exception.reviewException.ReviewNotFoundException;
import com.jumanji.capston.service.interfaces.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements BasicService<Review, Review.Request, String> {
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
    @Autowired
    OrderRepository orderRepository;


    @Override
    public Review get(@Nullable String authorization, String... reviewId) {
        return isPresent(reviewId[0]);
    }

    public List<Review> getMyReviewList(String authorization){
        String loginId = userService.getMyId(authorization);
        List<Review> reviewList = reviewRepository.findAllByUser_IdOrderByIdDesc(loginId);
        return reviewList;
    }

    @Override
    public List<Review> getList(@Nullable String authorization,String... shopId) {
        return reviewRepository.findAllByShopIdOrderByRegTimeDesc(shopId[0]);
    }

    @Override
    public Review post(@Nullable String authorization, Review.Request request) {
        System.out.println("리뷰등록's shopId : " + request.getShopId());
        System.out.println("리뷰등록's orderId : " + request.getOrderId());

        String loginId = userService.getMyId(authorization);
        String uri = "shop/" + request.getShopId() + "/review/"; // TODO 얘를 storage service에서 만들어 주는 메소드를 만들어야 할듯.
        String imgPath = "";
        Timestamp orderId = new Timestamp(Long.parseLong(request.getOrderId()));


        // 유효성 검사
        User user = userService.isPresent(loginId); // 유저 유효성 검사.
        Shop shop = shopService.isPresent(request.getShopId()); //
        Order order = orderService.isPresent(orderId);
        if(!order.getStatus().equals("pd"))throw new OrderNotPaidException(); //결제가 아니면 결제완료부터 하라 함.
        isEmpty(request.getOrderId()); // 같은 주문 번호로 리뷰 여러번 쓰는거 막기
        //TODO 추후에 결제완료 후 일정 시간 뒤에 적게 해야 함.
//        System.out.println(request.getImg().getName());
        if (request.getImg() != null)
            imgPath = storageService.store(request.getImg(), request.getImg().getResource().getFilename().replace(" ", "_"), uri.split("/"));
        Date regDate = new Date();
        Timestamp regTime = new Timestamp(regDate.getTime());
        Review review;
        String reviewId = request.getShopId().substring(0,2) + DateOperator.dateToYYYYMMDD(regDate, false);
        System.out.println("개수 가져오기 전 reivewId : " + reviewId);
        int countDayReviews = reviewRepository.countByIdStartingWith(reviewId);
        System.out.println("해당 날짜 리뷰 개수 : " + countDayReviews);
        reviewId = StringUtils.append(reviewId, String.format("%03d", countDayReviews));
        System.out.println("개수 붙인 후 reivewId : " + reviewId);
//        order.setReviewedY();
        orderRepository.save(order);
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
        System.out.println("등록된 리뷰의 imgUrl : " + review.getImgUrl() );
        return review;
    }

    @Override
    public Review patch(String authorization, Review.Request request) {
        return null;
    }

    @Override
    public void delete(@Nullable String authorization, String... reviewId) {
        String loginId = userService.getMyId(authorization);
        // 유효성 검사
        userService.isPresent(loginId); // 요청 유저가 존재하는지.
        Review review = isOwnReview(loginId, reviewId[0]); // 자신의 리뷰인지. 안에서 리뷰 존재여부 확인.
        // 그 외의 유효성 검사는 등록 시에 진행하였기 때문에 여기까지만.

        reviewRepository.delete(review);
    }

    @Override
    public Review isPresent(String reviewId) {
        Optional<Review> review = reviewRepository.findById(reviewId);
        if(review.isPresent())return review.get();
        throw new ReviewNotFoundException();
    }

    @Override
    public boolean isEmpty(String orderId) {
        Optional<Review> review = reviewRepository.findByOrderId(new Timestamp(Long.parseLong(orderId)));
        if(review.isEmpty())return true;
        throw new ReviewHasExistException();
    }

    public Review isOwnReview(String loginId, String reviewId){
        Review r = (Review) isPresent(reviewId);
        String reviewer = r.getUser().getId();
        System.out.println("리뷰 작성자 : " + reviewer);
        System.out.println("삭제 요청자 : " + loginId);
        if(reviewer.equals(loginId))return r;
        throw new ReviewIsNotYoursException();
    }
}
