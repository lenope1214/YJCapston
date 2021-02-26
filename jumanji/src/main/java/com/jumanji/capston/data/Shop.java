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
    //private Integer id; //매장번호
    private String id; //매장번호
    @Column(name="shop_name", length = 50, nullable = false)
    private String name; //매장이름
    @Column(name="shop_intro", length = 250)
    private String intro; //매장소개
    @Column(name="shop_open", nullable = false)
    private String open; //오픈시간
    @Column(name="shop_close", nullable = false)
    private String close; //마감시간
    @Column(name="shop_addr", length = 120,nullable = false)
    private String address; //매장주소
    @Column(name="shop_cat", length = 2)
    private String category; //카테고리
    @Column
    private char is_re_pos; //예약가능여부
    @ManyToOne
    @JoinColumn(name="mem_id")
    private Member mem_id;

}
