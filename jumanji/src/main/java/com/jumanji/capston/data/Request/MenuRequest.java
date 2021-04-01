package com.jumanji.capston.data.Request;

import lombok.Data;

@Data
public class MenuRequest {
    private String shopId;
    private String menuId;
    private String name;
    private String intro;
//    private String imgUrl; // 이미지 경로..??
    private int price;  // 가격
    private int duration; // 분단위
//    private MultipartFile img;
}