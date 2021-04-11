package com.jumanji.capston.service;

import com.jumanji.capston.data.Bucket;
import com.jumanji.capston.data.Shop;
import com.jumanji.capston.data.User;
import com.jumanji.capston.repository.BucketRepository;
import com.jumanji.capston.service.exception.BucketException.BucketHasExistException;
import com.jumanji.capston.service.exception.BucketException.BucketNotFoundException;
import com.jumanji.capston.service.interfaces.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;


@Service
public class BucketServiceImpl implements BucketService, BasicService {
    @Autowired
    BucketRepository bucketRepository;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ShopServiceImpl shopService;

    @Override
    public ResponseEntity<?> get(String bucketId) {
        isPresent(bucketId);
        Bucket bucket = bucketRepository.findById(bucketId).get();
        Bucket.Response response =
                new Bucket.Response(bucket);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getList(String userId) {
        for(Bucket bucket : bucketRepository.findALLByUser_Id(userId)){
            System.out.println("출력");
        }
        return new ResponseEntity<>(bucketRepository.findALLByUser_Id(userId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> post(Bucket.Request request) {
        Bucket bucket;
        //isEmpty 는 필요 x 주문은 매번 새로운 애기 때문.
        System.out.println("매장번호의 매장이 있나요? " + shopService.isPresent(request.getShopId()));
        System.out.println("유저번호의 유저가 있나요? " + userService.isPresent(request.getUserId()));
        Shop shop = shopService.isPresent(request.getShopId()) ?
                shopService.shopRepository.findById(request.getShopId()).get() : null;
        User user = userService.isPresent(request.getUserId()) ?
                userService.getUserInfo(request.getUserId()): null;
        System.out.println("가져온 매장번호 : " + shop.getId());
        System.out.println("가져온 유저번호 : " + user.getId());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Timestamp requestTime = new Timestamp(System.currentTimeMillis());
        String bucketId = dateFormat.format(requestTime);
        bucket = Bucket.builder()
                .id(bucketId)
                .orderRequest(request.getOrderRequest())
                .quantity(request.getQuantity())
                .shop(shop)
                .user(user)
                .build();
        bucketRepository.save(bucket);
        Bucket.Response response = new Bucket.Response(bucket);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> patch(Bucket.Request request) {
        return null; // 주문수정은 .. 없어도 되지 않을까?
    }

    @Override
    public ResponseEntity<?> delete(String bucketId) {
        System.out.println("");
        isPresent(bucketId);
        Bucket bucket = bucketRepository.findById(bucketId).get();
        bucketRepository.delete(bucket);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public boolean isPresent(String id) {
        if (bucketRepository.findById(id).isPresent()) return true;
        throw new BucketNotFoundException(id);
    }

    @Override
    public boolean isEmpty(String id) {
        if (bucketRepository.findById(id).isEmpty()) return true;
        throw new BucketHasExistException();
    }

}
