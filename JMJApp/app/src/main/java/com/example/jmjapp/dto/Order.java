package com.example.jmjapp.dto;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String orderId;
    private String status;
    private String orderRequest;
    private int people;
    private int usePoint; // 사용된 포인트
    private int amount; // 가격 총합
    private Long arriveTime; // 가게 도착시간
    private String payTime; // 결제 일자 yyyyMMdd
    private String pg;
    private String payMethod; // 결제방식
    private String shopId;
    private String shopName;
    private String userName;
    private String reason;
    private char reviewed;
    private String userId;
    private char accept;
    private int compleAmount;

    @Data
    public static class OrderMenuList {
        private List<OrderMenu> orderMenuList;
    }
}
