package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name="memberHasCoupon")
public class MemberHasCoupon implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name="member_id",nullable = false)
    private Member member_id; //아이디
    @Id
    @ManyToOne
    @JoinColumn(name="coupon_id",nullable = false)
    private Coupon coupon_id;//쿠폰번호
    @Column(name="hasCou_qty",length=2)
    private int hascou_qty;//보유수량
}