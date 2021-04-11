package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.Bucket;
import com.jumanji.capston.data.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface ReviewService {
    public ResponseEntity<?> get(String reviewId);
    public ResponseEntity<?> getList();
    public ResponseEntity<?> post(Review.Request request);
    public ResponseEntity<?> patch(Review.Request request);
    public ResponseEntity<?> delete(String reviewId);
}
