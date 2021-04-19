package com.jumanji.capston.controller;

import com.jumanji.capston.data.Payment;
import com.jumanji.capston.service.PaymentServiceImpl;
import lombok.Getter;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class PaymentController {

    @Autowired
    private PaymentServiceImpl paymentService;

    @Transactional
    @PostMapping("payment")
    public ResponseEntity<?> postPayment(@RequestBody Payment.Request request) {
        return paymentService.post(request);
    }

}
