package com.jumanji.capston.service;

import com.jumanji.capston.data.Payment;
import com.jumanji.capston.repository.PaymentRepository;
import com.jumanji.capston.service.interfaces.BasicService;
import com.jumanji.capston.service.interfaces.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService, BasicService {
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    OrderServiceImpl orderService;

    @Override
    public ResponseEntity<?> get(String paymentId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getList(String cartId) {
        return null;
    }

    @Override
    public ResponseEntity<?> post(Payment.Request request) {
        return null;
     }

    @Override
    public ResponseEntity<?> patch(Payment.Request request) {
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
