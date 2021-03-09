package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="coupons")
public class Coupon {
    @Id
    @Column(length = 9,nullable = false)
    private String coupon_id;//쿠폰번호
    @Column(length = 30,nullable = false)
    private String coupon_name;//쿠폰이름
//    @ManyToOne
//    @JoinColumn(name="account",nullable = false)
//    private Account id; //아이디
}
