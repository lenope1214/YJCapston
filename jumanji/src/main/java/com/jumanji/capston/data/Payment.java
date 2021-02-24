package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="payment")
public class Payment implements Serializable{
    @Id
    @ManyToOne
    @JoinColumn(name="odr_id")
    private Odr odr;//주문번호
    @Column(length=5)
    private int use_point;//사용포인트
    @Column(nullable = false)
    private Date pm_req_date;//결제일자
    @Column(nullable = false)
    private String pg;//결제방식
    @Column(length=6)
    private int pm_total_price;//총결제금액
    @Column(length=2)
    private char refund='N';//환불여부

}
