package com.jumanji.capston.service;

import com.jumanji.capston.data.Review;
import com.jumanji.capston.repository.ReviewRepository;
import com.jumanji.capston.service.interfaces.BasicService;
import com.jumanji.capston.service.interfaces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService, BasicService {
    @Autowired
    ReviewRepository reviewRepository;

    public Review findById(String id){
        return reviewRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("id를 확인해주세요!!!"));
    }


    public Review insert(Review review){
//        System.out.println();
        reviewRepository.save(review);
        return review;
    }

    public String delete(Review _review){
        System.out.println("_review : " + _review);
        Review review = reviewRepository.findById(_review.getId()).orElseThrow(()-> new IllegalArgumentException("id를 확인해주세요!!!"));
        reviewRepository.delete(review);
        return "ok";
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }



    @Override
    public ResponseEntity<?> get(String reviewId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getList() {
        return null;
    }

    @Override
    public ResponseEntity<?> post(Review.Request request) {
        return null;
    }

    @Override
    public ResponseEntity<?> patch(Review.Request request) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(String reviewId) {
        return null;
    }

    @Override
    public boolean isPresent(String id) {
        return false;
    }

    @Override
    public boolean isEmpty(String id) {
        return false;
    }
}
