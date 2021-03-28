package com.jumanji.capston.data;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name="shops")
public class Shop {
    @Id
    private String id; //매장번호 ( 사업자번호 )
    private String name; //매장이름
    private String intro; //매장소개
//    @JsonFormat(pattern = "HH:mm:ss")
//    private Date open_time; //오픈시간
//    @JsonFormat(pattern = "HH:mm:ss")
//    private Date close_time; //마감시간
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="HH:mm", timezone="Asia/Seoul")
    @Column(name = "open_time")
    private Date openTime;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="HH:mm", timezone="Asia/Seoul")
    @Column(name = "close_time")
    private Date closeTime;
    private String address; //매장주소
    @Column(name ="address_detail")
    private String addressDetail; //매장주소
    @Column(name = "is_rs_pos")
    private char isRsPos = 'Y'; //예약가능여부
    private String category;
    @ManyToOne
    @JoinColumn(name="owner_id")
    private User owner;
}
