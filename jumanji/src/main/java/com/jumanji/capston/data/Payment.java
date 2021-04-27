package com.jumanji.capston.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;


@Getter
//@Entity
//@Table(name="payments")
public class Payment implements Serializable {
//    @Id
//    @JoinColumn
//    @ManyToOne
    private Order id;

//    @Column(name="pay_time")
    private Timestamp payTime; // 결제 일자 yyyyMMdd
    private char refunded; // 환불여부
//    @Column(length = 7)
    private int amount; // 결제 금액
//    @Column(name = "pay_method")
    private String payMethod; // 결제방식
//    @Column(length = 9)
    private String pg;


    @Getter @AllArgsConstructor
    public static class Request{
        private Timestamp orderId;
        private int amount;
        private String pg;
        private String payMethod;
        private String shopId;
    }

    @Getter
    public static class Response{
        private Timestamp orderId;
        private String payTime;
        private String status;
        private int amount;
        private String payMethod;
        private String pg;

        public Response(Order order){
            this.orderId = order.getId();
            this.payTime = new DateFormat("yyyy-MM-dd'T'hh:mm:ss").format(order.getPayTime());
            this.status = order.getStatus();
            this.amount = order.getAmount();
            this.payMethod = order.getPayMethod();
            this.pg = order.getPg();
        }
    }
}