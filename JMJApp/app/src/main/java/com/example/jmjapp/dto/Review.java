package com.example.jmjapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private String reviewId;
    private String userId;
    private String shopId;
    private String content;
    private int parentId;
    private String regDate; // 등록일
    private int score;
    private String imgUrl;
    private String orderId;
}
