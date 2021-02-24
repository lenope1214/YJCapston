package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="coupon")
public class Coupon {
    @Id
    @Column(name="co_no",length = 6,nullable = false)
    private int co;//쿠폰번호
    @Column(length = 30,nullable = false)
    private String co_name;//쿠폰이름
    @ManyToOne
    @JoinColumn(name="member_id",nullable = false)
    private Member mem_id; //아이디
}
