package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name="coupons")
public class Coupon {
    @Id
    private String id;//쿠폰번호
    private String name;//쿠폰이름
    private Date begin_date; // 사용 마지막 기간
    private Date expiry_date; // 만료기간
//    @ManyToOne
//    @JoinColumn(name="account",nullable = false)
//    private Account id; //아이디
}
