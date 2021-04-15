package com.jumanji.capston.controller;

import com.jumanji.capston.data.Order;
import com.jumanji.capston.service.OrderMenuServiceImpl;
import com.jumanji.capston.service.OrderServiceImpl;
import com.jumanji.capston.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    UserServiceImpl userService;

//    @Transactional(readOnly = true)
//    @GetMapping("/cartId")
//    public ResponseEntity<?> getCartId(){
//        return orderService.getCartId();
//    }

    @Transactional(readOnly = true)
    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable Timestamp orderId){
        return orderService.get(orderId);
    }

    @Transactional(readOnly = true)
    @GetMapping("/order/myOrderList")
    public ResponseEntity<?> getOrderList(@RequestHeader String authorization){
        String loginId = userService.getMyId(authorization);
        return orderService.getList(loginId);
    }

    @Transactional
    @PostMapping("/order")
    public ResponseEntity<?> postOrder(@RequestHeader String authorization, @RequestBody Order.Request request){
//        System.out.println("request info \n" + request.getQuantity() +"\n" + request.getOrderRequest() +"\n" + request.getShopId() +"\n" + request.getUserId());
        return orderService.post(authorization, request);
    }

    @Transactional
    @PatchMapping("/order")
    public ResponseEntity<?> patchOrder(@RequestHeader String authorization, @RequestBody Order.Request request){
        return orderService.patch(authorization, request);
    }
}