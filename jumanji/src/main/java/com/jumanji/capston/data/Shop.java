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

    @Getter @Setter
    public static class Response{
        private String name;
        private String intro;
        private String address;
        private String addressDetail;
        private String category;
        private String openTime;
        private String closeTime;
        private int price;
        private int duration;
        private char isOpen;
        private char isRsPos;
//        private Resource img;
    }


    @Builder(builderMethodName = "createShop")
    public Shop(String shopId, String name, String intro, String openTime, String closeTime, String address, String addressDetail, String category, String imgPath, User owner) {
        this.id = shopId;
        this.name = name;
        this.intro = intro;
        this.openTime = toDate(openTime);
        this.closeTime = toDate(closeTime);
        this.address = address;
        this.addressDetail = addressDetail;
        this.category = category;
        this.isOpen = 'N';
        this.isRsPos = 'N';
        this.imgPath = imgPath;
        this.owner = owner;
    }

    public void update(Patch patch) {
        this.intro = patch.getIntro();
        this.openTime = toDate(patch.getOpenTime());
        this.closeTime = toDate(patch.getCloseTime());
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



    private Date toDate(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date parseDate = null;
        try {
            parseDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseDate;
    }
}
