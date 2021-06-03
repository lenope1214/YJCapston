package com.jumanji.capston.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Column(length = 13) // shop앞(2) + m+ yyyyMMdd + count(2)
    private String id; // 메뉴번호 ( 매장번호 + 메뉴이름 )

    @Column(length = 90)
    private String name; // 메뉴 이름
    @Column(length = 250)
    private String intro; //메뉴설명
    @Column(name = "is_sale")
    private char isSale = 'Y'; // 판매중
    @Column(name = "is_popular")
    private char isPopular = 'N'; // 인기메뉴여부
    @Column(length = 6)
    private int price = 0; // 가격
    @Column(length = 3)
    private int duration; // 걸리는 시간 = 조리시간 + ...
    @Column(name = "img_url")
    private String imgPath; // 이미지 상대 경로
    @Column(name = "reg_date", updatable = false)
    private Date regDate = new Date();
    @Column(name = "mod_date")
    private Date modDate = new Date();

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Shop shop;

    @OneToMany(mappedBy = "menu",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OptionGroup> optionGroupList = new ArrayList<>();

    public void patch(Menu.Request request){
        this.name = request.getName();
        this.intro = request.getIntro();
        this.price = request.getPrice();
        this.duration = request.getDuration();
//        private MultipartFile img;
    }

    @Builder(builderMethodName = "init")
    public Menu(String id, String name, String intro, int price, int duration, String imgPath, Shop shop) {
        this.id = id;
        this.name = name;
        this.shop = shop;
        this.intro = intro;
        this.price = price;
        this.duration = duration;
        this.isSale = 'N';
        this.isPopular = 'N';
        this.imgPath = imgPath;
    }

    public void reverseStatus(String target) {
        switch (target){
            case "popular":
                this.isPopular = this.isPopular == 'Y' ? 'N' : 'Y';
                break;
            case "sale":
                this.isSale = this.isSale == 'Y' ? 'N' : 'Y';
        }
    }

    @Getter @Setter
    public static class info{
        private String shopId;
        private String name;
        private String intro;
        private int price;
        private int duration;
        private MultipartFile img;
    }


    @Getter @Setter @Data
    public static class Request{
        private String shopId;
        private String menuId;
        private String name;
        private String intro;
        private int price;
        private int duration;
        private char isSale;
        private char isPopular;
        private MultipartFile img;

//        public String getMenuId(Menu.Request request){
//            return request.getShopId() + request.getName();
//        }
    }

    @Getter
    public static class Response{
        private String menuId;
        private String shopId;
        private String name;
        private String intro;
        private int price;
        private int duration;
        private char isSale;
        private char isPopular;
        private String imgPath;
        private List<OptionGroup> optionGroupList = new ArrayList<>();


        public Response(Menu menu){
//            this.id = menu.getId().substring(0,10);
            this.menuId = menu.getId();
            this.shopId = menu.getShop().getId();
            this.name = menu.getName();
            this.intro = menu.getIntro();
            this.price = menu.getPrice();
            this.duration = menu.getDuration();
            this.isSale = menu.getIsSale();
            this.isPopular = menu.getIsPopular();
            this.imgPath = menu.getImgPath();
            this.optionGroupList = menu.getOptionGroupList();
        }
//        public void parse(Menu menu){
//
//        }
    }

    public void update(Menu.Request request){
//        if(request.getName() != null){
//            this.id = Menu.subShopId(request.menuId) + request.getName();
//            this.name = request.getName();
//        }
        if(request.getIntro() != null)this.intro = request.getIntro();
        if(request.getPrice() != 0)this.price = request.getPrice();
        if(request.getDuration() != 0)this.duration = request.getDuration();
        this.modDate = new Date();
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
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="shop_id",nullable = false)
//    private Shop shop;//매장번호
//
//    public MenuId(){}
//}