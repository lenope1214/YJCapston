package com.jumanji.capston.data;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name="shops")
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
    @Column(name ="address_detail")
    private String addressDetail; //매장주소
    @Column(name = "is_rs_pos")
    private char isRsPos = 'Y'; //예약가능여부
    private String category;
    @Column(name = "is_open")
    private char isOpen = 'N';
    @Column(name = "img_path")
    private String imgPath;
    @ManyToOne
    @JoinColumn(name="owner_id")
    private User owner;



    @Getter @Setter @AllArgsConstructor
    public static class info{
        private String id;
        private String name;
        private String intro;
        private String openTime;
        private String closeTime;
        private String address;
        private String addressDetail;
        private String category;
        private MultipartFile img;
    }

    @Getter@Setter
    public static class Request{
        private String shopId;
        private String category;
    }

    @Getter @Setter @AllArgsConstructor
    public static class Response{
        private String name;
        private String intro;
        private String address;
        private String addressDetail;
        private String category;
        private String openTime;
        private String closeTime;
        private char isOpen;
        private char isRsPos;

        public Response(Shop shop) {
            this.name = shop.getName();
            this.intro = shop.getIntro();
            this.address = shop.getAddress();
            this.addressDetail = shop.getAddressDetail();
            this.category = shop.getCategory();
            this.openTime = shop.dateToString(shop.getOpenTime());
            this.closeTime = shop.dateToString(shop.getCloseTime());
            this.isOpen = shop.getIsOpen();
            this.isRsPos = shop.getIsRsPos();
        }
    }


    @Builder(builderMethodName = "createShop")
    public Shop(String id, String name, String intro, Date openTime, Date closeTime, String address, String addressDetail, String category, String imgPath, User owner) {
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
    }

    public void update(Patch patch) {
        this.intro = patch.getIntro();
        this.openTime = stringToDate(patch.getOpenTime());
        this.closeTime = stringToDate(patch.getCloseTime());
        this.address = patch.getAddress();
        this.addressDetail = patch.getAddressDetail();
        this.category = patch.getCategory();
    }


    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch{
        private String shopId;
        private String intro;
        private String openTime;
        private String closeTime;
        private String address;
        private String addressDetail;
        private String category;
    }



    public Date stringToDate(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date parseDate = null;
        try {
            parseDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseDate;
    }

    public String dateToString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(date);
    }
}
