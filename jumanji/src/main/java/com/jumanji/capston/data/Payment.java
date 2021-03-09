package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name="payments")
public class Payment implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order_id ; // 주문번호

    @Column(length = 5)
    private int used_point; // 사용된 포인트
    @Column(name="pm_req_date")
    private Date req_date; // 결제 일자 yyyyMMdd
    @Column(length = 8, nullable = false)
    private String pg; // 결제방식
    @Column(name="pm_total_price", length = 6)
    private int total_price; // 결제 당시 금액
    @Column
    private char is_refund; // 환불여부
}
