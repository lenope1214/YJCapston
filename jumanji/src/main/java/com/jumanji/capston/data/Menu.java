package com.jumanji.capston.data;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
last update 2021-02-23
author 이성복
 */

@Getter
@Entity
@Table(name="menus")
public class Menu{

    @Id
    private String id; // 메뉴번호 ( 매장번호 + seq(2) )
    
    private String name; // 메뉴이름
    private String description; //메뉴설명
    @Column(name="is_sale")
    private char isSale = 'Y'; // 판매중
    @Column(name = "is_popular")
    private char isPopular = 'N'; // 인기메뉴여부
    private int price = 0; // 가격
    private int duration; // 소요시간
    @Column(name = "img_url")
    private String imgUrl; // 이미지 상대 경로
}

//@Getter
//@Embeddable
//@EqualsAndHashCode
//public class MenuId  implements Serializable {
//
////    column: id (should be mapped with insert="false" update="false"
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(insertable = false, updatable = false)
//    private Long menu_id ; // 메뉴번호
//
//    @ManyToOne
//    @JoinColumn(name="shop_id",nullable = false)
//    private Shop shop;//매장번호
//
//    public MenuId(){}
//}