package com.jumanji.capston.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


@Getter
//@Entity
//@Table(name="payments")
public class Payment implements Serializable {
//    @Id
//    @JoinColumn
//    @ManyToOne
    private Timestamp orderId;

//    @Column(name="pay_time")
    private String payTime; // 결제 일자 yyyyMMdd
    private String status; // 주문 상태 rd : 준비중, pd : 결제완료, rf : 환불됨
//    @Column(length = 7)
    private int amount; // 결제 금액
//    @Column(name = "pay_method")
    private String payMethod; // 결제방식
//    @Column(length = 9)
    private String pg;


    public Payment(Order order){
        this.orderId = order.getId();
        this.payTime = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").format(order.getPayTime());
        this.status = order.getStatus();
        this.amount = order.getAmount();
        this.payMethod = order.getPayMethod();
        this.pg = order.getPg();
    }


    @Getter @AllArgsConstructor @NoArgsConstructor
    public static class Request{
        private Timestamp orderId; // 주문번호
        private String holder; // 예금주
        private int amount; //
        private String pg;
        private String payMethod;
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
            this.payTime = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").format(order.getPayTime());
            this.status = order.getStatus();
            this.amount = order.getAmount();
            this.payMethod = order.getPayMethod();
            this.pg = order.getPg();
        }
    }
}