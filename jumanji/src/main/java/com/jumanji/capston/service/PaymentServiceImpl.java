package com.jumanji.capston.service;

import com.jumanji.capston.data.Payment;
import com.jumanji.capston.repository.PaymentRepository;
import com.jumanji.capston.service.interfaces.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService, BasicService {
    @Autowired
    PaymentRepository paymentRepository;

    public Payment findById(Long id){
        return paymentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("id를 확인해주세요!!!"));
    }


    public Payment insert(Payment _payment){
        return paymentRepository.save(_payment);

    }

    public String delete(Payment _payment){
        Payment payment  = paymentRepository.findById(_payment.getId()).orElseThrow(()-> new IllegalArgumentException("id를 확인해주세요!!!"));
        paymentRepository.delete(payment);
        return "ok";
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

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
