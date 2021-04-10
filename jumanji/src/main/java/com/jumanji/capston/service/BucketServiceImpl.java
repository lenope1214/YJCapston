package com.jumanji.capston.service;

import com.jumanji.capston.data.Bucket;
import com.jumanji.capston.data.Penalty;
import com.jumanji.capston.data.Shop;
import com.jumanji.capston.data.User;
import com.jumanji.capston.repository.BucketRepository;
import com.jumanji.capston.repository.PenaltyRepository;
import com.jumanji.capston.service.interfaces.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.util.List;


public class BucketServiceImpl implements BucketService {
    @Autowired
    BucketRepository bucketRepository;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ShopServiceImpl shopService;

    public boolean isPresent(String id){
        if(bucketRepository.findById(id).isPresent())return true;
//        throw
        return false; /** 이자리에 throw BucketNotFoundException 던져줘야함. **/
    }


    public Bucket insert(Bucket bucket){
        return bucketRepository.save(bucket);

    }


    @Override
    public ResponseEntity<?> get(String bucketId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getList() {
        return new ResponseEntity<>(bucketRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> post(Bucket.Request request) {
        Bucket bucket;
        Shop shop = shopService.isPresent(request.getShopId()) ?
                shopService.shopRepository.findById(request.getShopId()).get() : null;
        User user = userService.isPresent(request.getUserId()) ?
                userService.userRepository.findById(request.getUserId()).get() : null;
        bucket = Bucket.builder()
                .id(new Timestamp(System.currentTimeMillis()))
                .orderRequest(request.getOrderRequest())
                .shop(shop)
                .user(user)
                .build();
        bucketRepository.save(bucket);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> patch(Bucket.Request request) {
        return null;
    }

    public ResponseEntity<?> post(Bucket bucket) {
        return null;
    }

    @Override
    public ResponseEntity<?> patch(String bucketId) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(String bucketId) {
        System.out.println("");
        Penalty penalty  = .findById(bucketId).orElseThrow(()-> new IllegalArgumentException("id를 확인해주세요!!!"));
        penaltyRepository.delete(penalty);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
