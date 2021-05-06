package com.jumanji.capston.controller;

import com.jumanji.capston.data.Payment;
import com.jumanji.capston.data.externalData.iamport.Iamport;
import com.jumanji.capston.service.OrderServiceImpl;
import com.jumanji.capston.service.PaymentServiceImpl;
import com.jumanji.capston.service.external.IamportClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Query;

import java.sql.Timestamp;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class PaymentController {

    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private PaymentServiceImpl paymentService;
    @Autowired
    IamportClientService iamportClientService;

    @Transactional
    @GetMapping("/payment/{orderId}")
    public ResponseEntity<?> getPayment(@RequestHeader String authorization, @PathVariable Timestamp orderId){
        Payment payment = paymentService.get(authorization, orderId);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/payment/complite")
    public ResponseEntity<?> complePayment(@Query("imp_uid") String impUid, @Query("merchant_uid") String merchantUid){
        try {
            iamportClientService.paymentByMerchantUid(merchantUid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    @PostMapping("/payment")
    public ResponseEntity<?> postPayment(@RequestHeader String authorization, @RequestBody Payment.Request request) {
        // response 형태로 바꿔줘야함.
        return new ResponseEntity(paymentService.post(authorization, request), HttpStatus.CREATED);
    }



}
