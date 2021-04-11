package com.jumanji.capston.controller;

import com.jumanji.capston.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class OrderController  {
    @Autowired
    OrderServiceImpl orderService;



//    @Transactional(readOnly = true)
//    @GetMapping("/order/{orderId}")
//    public ResponseEntity<?> selectOrderByOrderId(@PathVariable long orderId){
//        return orderService.getOrderByOrderId(orderId);
//    }
//
//    @Transactional
//    @PostMapping("/order")
//    public ResponseEntity<?> postOrder(@RequestBody Order.Request request){
////        return orderService.postOrder(request);
//        return null;
//    }

}
