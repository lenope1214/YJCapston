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
    private String s_id; //매장번호
    @Column(length = 50, nullable = false)
    private String s_name; //매장이름
    @Column(length = 250)
    private String s_intro; //매장소개
    @Column(nullable = false)
    private Timestamp s_open; //오픈시간
    @Column(nullable = false)
    private Timestamp s_close; //마감시간
    @Column(length = 120,nullable = false)
    private String s_addr; //매장주소
    @Column(length = 2)
    private int s_category; //카테고리
    @Column
    private char s_possible; //예약가능여부
    @ManyToOne
    @JoinColumn(name="member_id",nullable = false)
    private Member mem_id;//아이디


}
