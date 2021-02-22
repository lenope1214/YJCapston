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
    @JoinColumn(name="odr")
    private Odr odr_id;//주문번호
    @Column(length=5)
    private int use_point;//사용포인트
    @Column
    private Date pm_req_date;//결제일자
    @Column
    private String pg;//결제방식

    //결제금액 !!일단보류!!
}
