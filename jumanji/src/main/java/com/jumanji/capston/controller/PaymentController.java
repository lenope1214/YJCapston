//package com.jumanji.capston.controller;
//
//import com.jumanji.capston.data.Order;
//import com.jumanji.capston.data.Payment;
//import com.jumanji.capston.service.OrderServiceImpl;
//import com.jumanji.capston.service.PaymentServiceImpl;
//import lombok.Getter;
//import oracle.jdbc.proxy.annotation.Post;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.*;
//
//import java.sql.Timestamp;
//
//@RestController
////@CrossOrigin(origins = "http://localhost:3000")
//@RequestMapping("/api/v1")
//public class PaymentController {
//
//    @Autowired
//    private OrderServiceImpl orderService;
//
//    @Transactional
//    @GetMapping("/payment/{orderId}")
//    public ResponseEntity<?> getPayment(@RequestHeader String authorization, @PathVariable Timestamp orderId){
//        Order order = orderService.get(authorization, orderId);
//        Order.Response response = new Order.Response(order);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    @Transactional
//    @PostMapping("/payment")
//    public ResponseEntity<?> postPayment(@RequestHeader String authorization, @RequestBody Payment.Request request) {
//        // response 형태로 바꿔줘야함.
//        return new ResponseEntity(orderService.post(authorization, request), HttpStatus.CREATED);
//    }
//
//}
