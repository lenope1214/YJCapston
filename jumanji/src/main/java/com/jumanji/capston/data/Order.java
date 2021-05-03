package com.jumanji.capston.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


@Getter
@Entity
@NoArgsConstructor
@Table(name = "ORDERS")
public class Order implements Serializable {
    @Id
    private Timestamp id; // 바구니번호 yyyyMMddhhmmss
    private String status = "rd"; // rd : 준비중, pd : 결제완료, rf : 환불
    @Column(name = "order_request")
    private String orderRequest; // 요청사항
    @Column(length = 2)
    private int people;
    @Column(name="use_point", length = 5)
    private int usePoint; // 사용된 포인트
    @Column(length = 8)
    private int amount; // 가격 총합
    @Column(name = "arrive_time")
    private Timestamp arriveTime; // 가게 도착시간
    @Column(name="pay_time")
    private Timestamp payTime; // 결제 일자 yyyyMMdd
    @Column(length = 9)
    private String pg;
    @Column(name = "pay_method")
    private String payMethod; // 결제방식


//    @Column
//    private char delay; // 딜레이 얘는 뭔지 모르겠다. 나중에 다시 생각



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", updatable = false) @JsonIgnore // 이거 없으면 fetchType lazy라서 json 변환중에 오류남.
    private Shop shop;
    @JoinColumn(name = "user_id", updatable = false)
    @ManyToOne(fetch = FetchType.LAZY) @JsonIgnore // 이거 없으면 fetchType lazy라서 json 변환중에 오류남.
    private User user;

    @Builder
    public Order(Timestamp id, Shop shop, User user) {
        this.id = id;
        this.shop = shop;
        this.user = user;
    }

    @Getter @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private Timestamp orderId;
        private String status;
        private String orderRequest;
        private int people;
        private int usePoint; // 사용된 포인트
        private int amount; // 가격 총합
        private Timestamp arriveTime; // 가게 도착시간
        private Timestamp payTime; // 결제 일자 yyyyMMdd
        private String pg;
        private String payMethod; // 결제방식
        private String shopId;
        private String userId;
    }

    @Getter
    public static class Response{
        private Timestamp orderId;
        private int people;
        private String orderRequest;
        private String shopName;
        private String userName;
        private int usePoint; // 사용된 포인트
        private int amount; // 가격 총합
        private int totalAmount; // 할인 적용 가격
        private Timestamp arriveTime; // 가게 도착시간
        private String payTime; // 결제 일자 yyyyMMdd
        private String pg;
        private String payMethod; // 결제방식

        public Response(Order order) {
            this.orderId = order.getId();
            this.shopName = order.getShop().getName();
            this.people = order.getPeople();
            this.orderRequest = order.getOrderRequest();
            this.usePoint = order.getUsePoint();
            this.userName = order.getUser().getName();
            this.amount = order.getAmount();
            this.arriveTime = order.getArriveTime();
            this.pg = order.getPg();
            this.payMethod = order.getPayMethod();
            if(payTime != null)this.payTime = DateOperator.dateToYYYYMMDD(order.getPayTime()) + DateOperator.dateToHHMM(order.getPayTime());
            this.totalAmount = order.getAmount() - order.getUsePoint();
        }
    }

    public void update(Request request){
        if(request.orderRequest != null)this.orderRequest = request.getOrderRequest();
        if(request.people != 0) this.people = request.getPeople();
    }

    public void pay(Payment.Request request){
        this.status = "pd";
        this.payMethod = request.getPayMethod();
        this.payTime = new Timestamp(System.currentTimeMillis());
        this.pg = request.getPg();
        this.amount = request.getAmount();
    }

    public void refund(){
        this.status = "rf";
    }
}