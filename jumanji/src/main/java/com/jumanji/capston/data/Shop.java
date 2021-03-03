package com.jumanji.capston.data;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;


@Getter
@Setter
@Entity
@Table(name="shop")
public class Shop {
    @Id
    @Column(name="shop_id",length = 10,nullable = false)
    private String id; //매장번호

    @Column(name="name", length = 50, nullable = false)
    private String name; //매장이름
    @Column(name="intro", length = 250)
    private String intro; //매장소개
    @Column(name="open_time", length = 4, nullable = false)
    private int open_time; //오픈시간
    @Column(name="close_time", length = 4,  nullable = false)
    private int close_time; //마감시간
    @Column(name="address", length = 120,nullable = false)
    private String address; //매장주소
    @Column(name="address_detail", length = 120,nullable = false)
    private String address_detail; //매장상세주소
//    @Column(name="shop_cat", length = 2)
//    private int category; //카테고리
    @Column
    private char is_rs_pos; //예약가능여부
    @ManyToOne
    @JoinColumn(name="owner_id")
    private Member owner_id;

}
