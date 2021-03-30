package com.jumanji.capston.data;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
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
    private char isOpen;
    @ManyToOne
    @JoinColumn(name="owner_id")
    private User owner;

    @Builder(builderMethodName = "createShop")
    public Shop(String shopId, String name, String intro, Date openTime, Date closeTime, String address, String addressDetail, String category) {
        this.id = shopId;
        this.name = name;
        this.intro = intro;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.address = address;
        this.addressDetail = addressDetail;
        this.category = category;
        this.isOpen = 'N';
        this.isRsPos = 'N';
    }

}
