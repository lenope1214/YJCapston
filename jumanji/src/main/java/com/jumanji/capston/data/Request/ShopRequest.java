package com.jumanji.capston.data.Request;

import lombok.Data;

@Data
public class ShopRequest  {
    private String shopId; //매장번호 ( 사업자번호 )
    private String name; //매장이름
    private String intro; //매장소개
    private String openTime;
    private String closeTime;
    private String address; //매장주소
    private String addressDetail; //매장주소
    private String category;
    private char isRsPos;
}