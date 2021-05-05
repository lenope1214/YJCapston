package com.jumanji.capston.controller;

import com.jumanji.capston.data.Review;
import com.jumanji.capston.data.Shop;
import com.jumanji.capston.service.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class ReviewController  {

    @Autowired
    ReviewServiceImpl reviewService;

    @Transactional
    @GetMapping("/review/{shopId}")
    public ResponseEntity<?> getReviewList(@PathVariable String shopId){
        System.out.println("getReviewList- shopId : " + shopId);
        List<Review> reviewList = reviewService.getList(shopId);
        List<Review.Response> response = new ArrayList<>();
        for(Review r : reviewList){
            response.add(new Review.Response(r));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/review") // Multipart-form 는 json이 아니기 때문에 바디 뺌.
    public ResponseEntity<?> postReview(@RequestHeader String authorization, Review.Request request){
        System.out.println("??" +request.getShopId());
        Review review = reviewService.post(authorization, request);
        Review.Response response = new Review.Response(review);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
