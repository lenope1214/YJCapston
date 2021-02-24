package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name="memberhascoupon")
public class MemberHasCoupon implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name="member_id",nullable = false)
    private Member mem_id; //아이디
//    @Id
//    @ManyToOne
//    @JoinColumn(name="co_no")
//    private Coupon co_no;//쿠폰번호
    @Column(length=2)
    private int hascou_qty;//보유수량
}
