package com.jumanji.capston.controller;

import com.jumanji.capston.data.Bucket;
import com.jumanji.capston.service.BucketServiceImpl;
import com.jumanji.capston.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BucketController {
    @Autowired
    BucketServiceImpl bucketService;
    @Autowired
    UserServiceImpl userService;


    @Transactional(readOnly = true)
    @GetMapping("/bucket/{bucketId}")
    public ResponseEntity<?> getBucket(@PathVariable String bucketId){
        return bucketService.get(bucketId);
    }

    @Transactional(readOnly = true)
    @GetMapping("/bucket/{userId}")
    public ResponseEntity<?> getBucketList(@RequestHeader String authorization, @PathVariable String userId){
        String loginId = userService.getMyId(authorization);
        return bucketService.getList(loginId);
    }

    @Transactional
    @PostMapping("/bucket")
    public ResponseEntity<?> postBucket(@RequestBody Bucket.Request request){
//        System.out.println("request info \n" + request.getQuantity() +"\n" + request.getOrderRequest() +"\n" + request.getShopId() +"\n" + request.getUserId());
        return bucketService.post(request);
    }
}