package com.jumanji.capston.controller;

import com.jumanji.capston.data.Order;
import com.jumanji.capston.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class OrderController  {
    @Autowired
    OrderService orderService;



    @Transactional(readOnly = true)
    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> selectOrderByOrderId(@PathVariable long orderId){
        return orderService.getOrderByOrderId(orderId);
    }

    @Transactional
    @PostMapping("/order")
    public ResponseEntity<?> postOrder(@RequestBody Order.Request request){
        return orderService.postOrder(request);
    }

}
