package com.jumanji.capston.controller;

import com.jumanji.capston.data.OrderMenu;
import com.jumanji.capston.service.OrderMenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OrderMenusController {
    @Autowired
    OrderMenuServiceImpl orderService;


    @Transactional(readOnly = true)
    @GetMapping("/order/{cartId}")
    public ResponseEntity<?> selectOrderByOrderId(@PathVariable String cartId) {
        return orderService.getOrderByCartId(cartId);
    }

    @Transactional
    @PostMapping("/order")
    public ResponseEntity<?> postOrder(@RequestBody OrderMenu.Request request) {
//        return orderService.postOrder(request);

        return orderService.post(request);
    }

    @Transactional
    @PatchMapping("/order")
    public ResponseEntity<?> patchOrder(@RequestBody OrderMenu.Request request) {
        return orderService.patch(request);
    }

}
