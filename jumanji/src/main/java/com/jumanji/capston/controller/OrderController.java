package com.jumanji.capston.controller;

import com.jumanji.capston.data.Order;
import com.jumanji.capston.service.OrderMenuServiceImpl;
import com.jumanji.capston.service.OrderServiceImpl;
import com.jumanji.capston.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<?> getOrder(@RequestHeader String authorization, @PathVariable Timestamp orderId){
        Order order =orderService.get(authorization,orderId);
        Order.Response response = new Order.Response(order);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/order")
    public ResponseEntity<?> getOrderList(@RequestHeader String authorization){
        List<Order> orderList = orderService.getList(authorization);
        List<Order.Response> response = new ArrayList<>();
        for(Order order : orderList ){
            response.add(new Order.Response(order));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/order")
    public ResponseEntity<?> postOrder(@RequestHeader String authorization, @RequestBody Order.Request request){
//        System.out.println("request info \n" + request.getQuantity() +"\n" + request.getOrderRequest() +"\n" + request.getShopId() +"\n" + request.getUserId());
        Order order = orderService.post(authorization, request);
        Order.Response response = new Order.Response(order);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Transactional
    @PatchMapping("/order")
    public ResponseEntity<?> patchOrder(@RequestHeader String authorization, @RequestBody Order.Request request){
        Order order = orderService.patch(authorization, request);
        Order.Response response = new Order.Response(order);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @Transactional
//    @DeleteMapping("/order/{orderId}")
//    public ResponseEntity<?> deleteOrder(@RequestHeader String authorization, @PathVariable Timestamp orderId){
//        orderService.delete(authorization, orderId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}