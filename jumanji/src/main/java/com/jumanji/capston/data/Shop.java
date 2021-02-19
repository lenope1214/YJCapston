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
    @Column(length = 10)
    private String s_id; //매장번호
    @Column(length = 50)
    private String s_name; //매장이름
    @Column(length = 255)
    private String s_intro; //매장소개
    @Column
    private Timestamp s_open; //오픈시간
    @Column
    private Timestamp s_close; //마감시간
    @Column(length = 120)
    private String s_addr; //매장주소
    @Column
    private int s_category; //카테고리
    @Column
    private char s_possible; //예약가능여부
    @ManyToOne
    @JoinColumn(name="account")
    private Account id;

}
