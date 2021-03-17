package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


@Getter
@Entity
@Table(name="payments")
public class Payment implements Serializable {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order_id ; // 주문번호
    private int use_point; // 사용된 포인트
    private Timestamp pay_time; // 결제 일자 yyyyMMdd
    private String pg; // 결제방식
    private int comple_pay; // 결제 당시 금액
}
