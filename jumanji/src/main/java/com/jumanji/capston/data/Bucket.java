package com.jumanji.capston.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Getter
@Entity
@Table(name = "buckets")
public class Bucket implements Serializable {
    @Id
//    @Column(insertable = false, updatable = false)
    private Timestamp id; // 바구니번호 yyyyMMddhhmmss

    @Column(length = 2)
    private int quantity; // 메뉴 수량
    @Column(name = "order_request")
    private String orderRequest; // 요청사항
    @ManyToOne
    @JoinColumn(name = "shop_id", insertable = false, updatable = false)
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Builder
    public Bucket(Timestamp id, int quantity, String orderRequest, Shop shop, User user) {
        this.id = id;
        this.quantity = quantity;
        this.orderRequest = orderRequest;
        this.shop = shop;
        this.user = user;
    }

    @Getter @AllArgsConstructor
    public class Request {
        private int quantity;
        private String orderRequest;
        private String shopId;
        private String userId;
    }

    @Getter
    public class Response{
        private int quantity;
        private String orderRequest;
        private String shopId;
        private String userId;

        public Response(Bucket bucket) {
            this.quantity = bucket.quantity;
            this.orderRequest = bucket.getOrderRequest();
            this.shopId = bucket.getShop().getId();
            this.userId = bucket.getUser().getId();
        }
    }
}