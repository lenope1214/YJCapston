package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name="coupon")
public class Coupon {
    @Id
    @Column(name="coupon_id",length = 20,nullable = false)
    private int coupon_id;//쿠폰번호
    @Column(name="coupon_name",length = 30,nullable = false)
    private String coupon_name;//쿠폰이름
    @Column(name="coupon_limit",nullable = false)
    private Timestamp coupon_limit;//쿠폰만료기간
//    @ManyToOne
//    @JoinColumn(name="account",nullable = false)
//    private Account id; //아이디
}
