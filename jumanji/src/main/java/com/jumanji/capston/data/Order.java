package com.jumanji.capston.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Getter
@Entity
@NoArgsConstructor
@Table(name = "ORDERS")
public class Order implements Serializable {
    @Id
    private Timestamp id; // 바구니번호 yyyyMMddhhmmss
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
    @JoinColumn(name = "shop_id", updatable = false)
    @JsonIgnore
    private Shop shop;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false)
    @JsonIgnore
    private User user;

    @Builder
    public Order(Timestamp id, int people, String orderRequest, Shop shop, User user) {
        this.id = id;
        this.people = people;
        this.orderRequest = orderRequest;
        this.shop = shop;
        this.user = user;
    }

    @Getter @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private Timestamp orderId;
        private int people;
        private String orderRequest;
        private String shopId;
        private String userId;
    }

    @Getter
    public static class Response{
        private Timestamp orderId;
        private int people;
        private String orderRequest;
        private String shopId;
        private String userId;

        public Response(Order cart) {
            this.orderId = cart.getId();
            this.people = cart.getPeople();
            this.orderRequest = cart.getOrderRequest();
            this.shopId = cart.getShop().getId();
            this.userId = cart.getUser().getId();
        }
    }

    public void update(Request request){
        if(request.orderRequest != null)this.orderRequest = request.getOrderRequest();
        if(request.people != 0) this.people = request.getPeople();
    }
}