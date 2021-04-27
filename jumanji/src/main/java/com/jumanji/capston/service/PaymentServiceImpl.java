package com.jumanji.capston.service;

import com.jumanji.capston.data.Order;
import com.jumanji.capston.data.Payment;
import com.jumanji.capston.service.interfaces.BasicService;
import com.jumanji.capston.service.interfaces.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService, BasicService {
    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    UserServiceImpl userService;


    @Override
    public Payment get(String authorization, String paymentId) {
        return null;
    }

    @Override
    public List<Payment> getList(String orderId) {
        return null;
    }

    @Override
    public Payment post(String authorization, Payment.Request request) {
        String loginId = userService.getMyId(authorization);
        Order order = orderService.getOrderInfo(request.getOrderId());

        order.pay(request);
        Payment.Response response = new Payment.Response(order);
        return null;
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
