package com.jumanji.capston.repository;

import com.jumanji.capston.data.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, String> {


    List<Review> findAllByShopIdOrderByRegTimeDesc(String shopId);


    int countByIdStartingWith(String reviewId);

    Optional<Review> findByOrderId(Timestamp orderId);

    @Query(value = "select r.id from ORDERS o\n" +
            "left join REVIEWS R on o.ID = R.ORDER_ID\n" +
            "where r.USER_ID = :userId",nativeQuery = true)
    String myReviewList(String userId);
}
