package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.tuple.GeneratedValueGeneration;

import javax.persistence.*;
import javax.persistence.Table;
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
    private Date begin_date= new Date(); // 사용 마지막 기간
    private Date expiry_date; // 만료기간
//    @ManyToOne
//    @JoinColumn(name="account",nullable = false)
//    private Account id; //아이디
}
