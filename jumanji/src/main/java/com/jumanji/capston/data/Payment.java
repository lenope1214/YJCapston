package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@Entity
@javax.persistence.Table(name="payment")
public class Payment implements Serializable {
    @Id
    @JoinColumn
    @Column(length = 16)
    private Order order_id ; // 주문번호

    @Column(length = 5)
    private int used_point; // 사용된 포인트
    @Column(length = 5)
    private Date pm_req_date; // 결제 일자 yyyyMMdd
    @Column(length = 8, nullable = false)
    private String pg; // 결제방식
    @Column(length = 6)
    private int pm_total_price; // 결제 당시 금액
    @Column
    private char is_refund; // 환불여부
}
