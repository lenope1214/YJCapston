//package com.example.restapi.model;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import javax.persistence.Table;
//import java.io.Serializable;
//import java.util.Date;
//
///*
//last update 2021-02-23
//author 이성복
// */
//
//@Getter
//@Setter
//@Entity
//@Table(name="menu")
//public class Menu implements Serializable {
//    @Id
//    @Column(name="menu_id", length = 3)
//    private String no ; // 메뉴번호
//
//    @Column(name="menu_name", length = 30, nullable = false)
//    private String name; // 이름
//    @Column(name="menu_desc", nullable = false)
//    private String desc; // 설명
//    @Column
//    private char is_sale = 'Y'; // 판매중
//    @Column
//    private char is_popular = 'N'; // 인기메뉴여부
//    @Column(name="menu_price",length = 5, nullable = false)
//    private int price = 0; // 가격
//    @Column(name="menu_dur")
//    private Date duration; // 소요시간
//    @Column(name="menu_img_url", length = 150)
//    private String img_url; // 이미지 상대 경로
//}
//
//
////@Entity
////@SequenceGenerator(
////        name = "MENU_SEQ_GENERATOR",
////        sequenceName = "MENU_SEQ",
////        initialValue = 1,
////        allocationSize = 1
////)
////@Table(name = "")