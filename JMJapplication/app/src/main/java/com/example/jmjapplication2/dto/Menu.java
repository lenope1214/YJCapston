package com.example.jmjapplication2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    private String id; // 메뉴번호 ( 매장번호 + seq(2) )

    private String name; // 메뉴이름
    private String description; //메뉴설명
    private char is_sale = 'Y'; // 판매중
    private char is_popular = 'N'; // 인기메뉴여부
    private int price = 0; // 가격
    private int duration; // 소요시간
    private String img_url; // 이미지 상대 경로
}
