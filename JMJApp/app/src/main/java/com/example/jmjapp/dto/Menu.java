package com.example.jmjapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    private String id; // 메뉴번호 ( 매장번호 + seq(2) )

    private String name; // 메뉴이름
    private String intro; //메뉴설명
    private char isSale; // 판매중
    private char isPopular; // 인기메뉴여부
    private int price; // 가격
    private int duration; // 소요시간
    private String imgPath; // 이미지 상대 경로


    public Menu(int menuNumber, String name, int price) {
        this.name = name;
        this.price = price;
    }
}
