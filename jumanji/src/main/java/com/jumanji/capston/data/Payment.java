package com.jumanji.capston.data;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Getter
@Entity
@Table(name="payments")
public class Payment implements Serializable {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order ; // 주문번호
    @Column(name="use_point")
    private int usePoint; // 사용된 포인트
    @Column(name="pay_time")
    private Timestamp payTime; // 결제 일자 yyyyMMdd
    private String pg; // 결제방식
    @Column(name="comple_pay")
    private int complePay; // 결제 당시 금액
    @Column(name = "is_refund")
    private char isRefund; // 환불 여부

    public class Request{

    }
}
