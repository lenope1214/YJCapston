package com.example.restapi.model;


import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name="shop")
public class Shop {
    @Id
    @Column(length = 10,nullable = false)
    private String id; //매장번호

    @Column(length = 50, nullable = false)
    private String name; //매장이름
    @Column(length = 250)
    private String intro; //매장소개
    @Column(name="open_time", length = 4, nullable = false)
    private int open; //오픈시간
    @Column(name="close_time", length = 4,  nullable = false)
    private int close; //마감시간
    @Column(length = 120,nullable = false)
    private String address; //매장주소
    @Column
    private String category; //카테고리
    @Column
    private char is_re_pos; //예약가능여부
    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member_id;
}
