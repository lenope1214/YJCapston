package com.jumanji.capston.data;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "shops")
@NoArgsConstructor
public class Shop {
    @Id
    private String id; //매장번호 ( 사업자번호 )
    private String name; //매장이름
    private String intro; //매장소개
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(name = "open_time")
    private Date openTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(name = "close_time")
    private Date closeTime;
    private String address; //매장주소
    @Column(name = "address_detail")
    private String addressDetail; //매장주소
    @Column(name = "is_rs_pos")
    private char isRsPos = 'Y'; //예약가능여부
    private String category;
    @Column(name = "is_open")
    private char isOpen = 'N';
    @Column(name = "img_path")
    private String imgPath;
    @Column(length = 11)
    private String phone;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private User owner;



    @Getter
    @NoArgsConstructor
    public static class PatchRequest {
        private String shopId;
        private String name;
        private String intro;
        private String openTime;
        private String closeTime;
        private String address;
        private String addressDetail;
        private String category;
        private String phone;
        private MultipartFile img;
    }

    @Getter
    @AllArgsConstructor
    public static class PostRequest {
        private String shopId;
        private String name;
        private String intro;
        private String openTime;
        private String closeTime;
        private String address;
        private String addressDetail;
        private String category;
        private String phone;
        private MultipartFile img;
    }

    @Getter
    @Setter
    public static class Response implements Serializable {
        private String shopId;
        private String name;
        private String intro;
        private double score;
        private int reviews;
        private String address;
        private String addressDetail;
        private String category;
        private String openTime;
        private String closeTime;
        private char isOpen;
        private char isRsPos;
        private String imgPath;
        private String phone;
        private String ownerId;
        @Setter
        private char marked = 'N';

        public Response(Shop.Dao dao) {
            this.shopId = dao.getShopId();
            this.name = dao.getName();
            this.intro = dao.getIntro();
            this.address = dao.getAddress();
            this.addressDetail = dao.getAddressDetail();
            this.category = dao.getCategory();
            this.openTime = dao.getOpenTime();
            this.closeTime = dao.getCloseTime();
            this.isOpen = dao.getIsOpen();
            this.isRsPos = dao.getIsRsPos();
            this.imgPath = dao.getImgPath();
            this.score = Double.parseDouble(dao.getScore());
            this.reviews = Integer.parseInt(dao.getReviews());
        }

        public Response(Shop.Dao dao, char marked){
            this(dao);
            this.marked = marked;
        }

        public Response(Shop shop) {
            this.shopId = shop.getId();
            this.name = shop.getName().replace("_", " ");
            this.intro = shop.getIntro();
            this.address = shop.getAddress();
            this.addressDetail = shop.getAddressDetail();
            this.category = shop.getCategory();
            this.openTime = DateOperator.dateToHHMM(shop.getOpenTime(), true);
            this.closeTime = DateOperator.dateToHHMM(shop.getCloseTime(), true);
            this.isOpen = shop.getIsOpen();
            this.isRsPos = shop.getIsRsPos();
            this.imgPath = shop.getImgPath();
            this.phone = shop.getPhone();
            this.ownerId = shop.getOwner().getId();
        }


        public Response(Shop shop, char marked) {
            this(shop);
            this.marked = marked;
        }
    }


    @Builder(builderMethodName = "insertShop")
    public Shop(String id, String name, String intro, Date openTime, Date closeTime, String address, String addressDetail, String category, String imgPath, User owner, String phone) {
        this.id = id;
        this.name = name;
        this.intro = intro;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.address = address;
        this.addressDetail = addressDetail;
        this.category = category;
        this.isRsPos = 'N';
        this.imgPath = imgPath;
        this.owner = owner;
        this.phone = phone;
    }

    public void update(Shop.PatchRequest patch) {
        if (patch.getPhone() != null) this.phone = patch.getPhone();
        if (patch.getIntro() != null) this.intro = patch.getIntro();
        if (patch.getOpenTime() != null) this.openTime = DateOperator.stringToMilisecond(patch.getOpenTime());
        if (patch.getCloseTime() != null) this.closeTime = DateOperator.stringToMilisecond(patch.getCloseTime());
        if (patch.getAddress() != null) this.address = patch.getAddress();
        if (patch.getAddressDetail() != null) this.addressDetail = patch.getAddressDetail();
        if (patch.getCategory() != null) this.category = patch.getCategory();
    }

    @ToString
    @Getter
    @NoArgsConstructor
    public static class Info {
        Shop.Response shopInfo;
        List<Menu.Response> menuList = new ArrayList<>();
        List<Review.Response> reviewList = new ArrayList<>();

        public Info(Shop shop, char marked) {
            this.shopInfo = new Response(shop, marked);
        }


        public Info(Shop shop, List<Menu> menuList, List<Review> reviewList, char marked) {
            this(shop, marked);
            setInfo(menuList, reviewList);
        }

        /**
         * @param menuList   식당의 메뉴 리스트
         * @param reviewList 식당의 리뷰 리스트
         */
        public void setInfo(List<Menu> menuList, List<Review> reviewList) {
            for (Menu menu : menuList) {
                this.menuList.add(new Menu.Response(menu));
            }
            for (Review review : reviewList) {
                this.reviewList.add(new Review.Response(review));
            }
        }
    }

    public interface Dao {
        String getShopId();
        String getName();
        String getIntro();
        String getAddress();
        String getAddressDetail();
        String getCategory();
        String getOpenTime();
        String getCloseTime();
        Character getIsOpen();
        Character getIsRsPos();
        String getImgPath();
        String getScore();
        String getReviews();
    }
}