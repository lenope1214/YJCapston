package com.jumanji.capston.data.Request;

import lombok.Data;

@Data
public class ShopRequest  {
    private String id; //매장번호 ( 사업자번호 )
    private String name; //매장이름
    private String intro; //매장소개
    private String open_time;
    private String close_time;
    private String address; //매장주소
    private String address_detail; //매장주소
    private String category;
    private char is_rs_pos;
}