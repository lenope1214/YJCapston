package com.jumanji.capston.data;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


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
    private int open_time;
    private int close_time;
    private String address; //매장주소
    private String address_detail; //매장주소
    private char is_rs_pos = 'Y'; //예약가능여부
    @ManyToOne
    @JoinColumn(name="owner_id")
    private User owner_id;

}
