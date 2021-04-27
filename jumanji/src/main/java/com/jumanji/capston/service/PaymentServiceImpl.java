package com.jumanji.capston.service;

import com.jumanji.capston.data.Order;
import com.jumanji.capston.data.Payment;
import com.jumanji.capston.service.interfaces.BasicService;
import com.jumanji.capston.service.interfaces.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService, BasicService {
    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    UserServiceImpl userService;


    @Override
    public Payment get(String authorization, Timestamp orderId) {
        return null;
    }

    @Override
    public List<Payment> getList(String orderId) {
        return null;
    }

    @Override
    public Payment post(String authorization, Payment.Request request) {
        String loginId = userService.getMyId(authorization);
        userService.isPresent(loginId); // 그 사용자가 맞는지 확인
        Order order = orderService.get(authorization, request.getOrderId()); // 해당 사용자의 주문이 맞는지
        order.pay(request);
        Payment payment = new Payment(order);
        System.out.println("결제시간 : " + payment.getPayTime());

        return payment;
    }

    @Override
    public Payment patch(String authorization, Payment.Request request) {
        return null;
    }

    @Override
    public boolean isPresent(String id) {
        return false;
    }

    @Override
    public boolean isEmpty(String id) {
        return false;
    }
}
