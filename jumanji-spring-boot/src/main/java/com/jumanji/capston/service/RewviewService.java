package com.jumanji.capston.service;

import com.jumanji.capston.data.Review;
import com.jumanji.capston.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewviewService {
    @Autowired
    ReviewRepository reviewRepository;

    public Review findById(Long id){
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
}
