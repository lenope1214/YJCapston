package com.jumanji.capston.data;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/*
last update 2021-02-23
author 이성복
 */

@Getter
@Entity
@Table(name = "menus")
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    @Id
    private String id; // 메뉴번호 ( 매장번호 + seq(2) )

    private String name; // 메뉴이름
    private String intro; //메뉴설명
    @Column(name = "is_sale")
    private char isSale = 'Y'; // 판매중
    @Column(name = "is_popular")
    private char isPopular = 'N'; // 인기메뉴여부
    private int price = 0; // 가격
    @Column(length = 3)
    private int duration; // 걸리는 시간 = 조리시간 + ...
    @Column(name = "img_url")
    private String imgUrl; // 이미지 상대 경로
    @Column(name = "reg_date", updatable = false)
    private Date regDate = new Date();
    @Column(name = "mod_date")
    private Date modDate = new Date();

    @Builder(builderMethodName = "info")
    public Menu(String id, String name, String intro, int price, int duration, String imgUrl) {
        this.id = id;
        this.name = name;
        this.intro = intro;
        this.price = price;
        this.duration = duration;
        this.imgUrl = imgUrl;
    }

    public static class info{
        private String name;
        private String intro;
        private int price;
        private int duration;
        private MultipartFile img;
    }

    @Getter
    @Setter
    public static class Request{
        private String menuId;
    }
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