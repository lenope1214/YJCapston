package com.jumanji.capston.controller;

import com.jumanji.capston.data.Review;
import com.jumanji.capston.data.Shop;
import com.jumanji.capston.service.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Path;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/")
public class ReviewController  {

    @Autowired
    ReviewServiceImpl reviewService;

    @Transactional
    @GetMapping("/users/reviews")
    public ResponseEntity<?> getMyReviews(@RequestHeader String authorization){
        List<Review> reviewList = reviewService.getMyReviewList(authorization);
        List<Object> response = new ArrayList<>();
        for(Review review : reviewList){
            response.add(new Review.Response(review));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("reviews/{shopId}")
    public ResponseEntity<?> getReviewList(@PathVariable String shopId){
        List<Review> reviewList = reviewService.getList(null, shopId);
        List<Review.Response> response = new ArrayList<>();
        for(Review r : reviewList){
            response.add(new Review.Response(r));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("reviews") // Multipart-form 는 json이 아니기 때문에 바디 뺌.
    public ResponseEntity<?> postReview(@RequestHeader String authorization, Review.Request request){
        System.out.println(request.toString());
        Review review = reviewService.post(authorization, request);
        Review.Response response = new Review.Response(review);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Transactional
    @PatchMapping("reviews")
    public ResponseEntity<?> patchReview(@RequestHeader String authorization, @RequestBody Review.Request request){
        return null;
    }

    @Transactional
    @DeleteMapping("reviews/{reviewId}")
    public ResponseEntity<?> deleteReview(@RequestHeader String authorization, @PathVariable String reviewId){
        reviewService.delete(authorization, reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
