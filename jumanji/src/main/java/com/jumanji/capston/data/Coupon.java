package com.jumanji.capston.data;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@Table(name="coupons")
public class Coupon {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(length = 12)
    private Long id;//쿠폰번호
    @Column(length = 30)
    private String name;//쿠폰이름
    @Column(name = "begin_date")
    private final Date beginDate= new Date(); // 사용 마지막 기간
    @Column(name = "expiry_date")
    private Date expiryDate; // 만료기간
//    @ManyToOne
//    @JoinColumn(name="account",nullable = false)
//    private Account id; //아이디
}
