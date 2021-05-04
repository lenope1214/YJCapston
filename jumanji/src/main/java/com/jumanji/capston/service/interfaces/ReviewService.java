package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.Review;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewService {
    public Review get(String reviewId);
    public List<Review> getList(String shopId);
    public Review post(String authorization, Review.Request request);
    public Review patch(String authorization, Review.Request request);
    public void delete(String authorization, String reviewId);
}
