package com.jumanji.capston.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Getter
@Entity
@Table(name="payments")
public class Payment implements Serializable {
    @Id
    @Column(length = 16)
    private String id;

    @Column(name="pay_time")
    private Timestamp payTime; // 결제 일자 yyyyMMdd
    private char refunded; // 환불여부
    @Column(length = 7)
    private int price; // 결제 금액
    @Column(name = "pay_method")
    private String payMethod; // 결제방식


    @Getter @AllArgsConstructor
    public static class Request{
        private Timestamp orderId;
        private int price;
        private String payMethod;
    }

    @Getter
    public static class Response{
        private String payId;
        private Timestamp payTime;
        private char refunded;
        private int price;
        private String payMethod;
    }
}
