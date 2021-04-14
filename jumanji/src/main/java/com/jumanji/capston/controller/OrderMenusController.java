package com.jumanji.capston.controller;

import com.jumanji.capston.data.OrderMenu;
import com.jumanji.capston.service.OrderMenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/api/v1")
public class OrderMenusController {
    @Autowired
    OrderMenuServiceImpl orderMenuService;


    @Transactional(readOnly = true)
    @GetMapping("/orderMenu/{orderMenuId}")
    public ResponseEntity<?> selectOrderByOrderId(@PathVariable Timestamp orderMenuId) {
        System.out.println("orderMenuId : " + orderMenuId);
        return orderMenuService.getOrderMenuByCartId(orderMenuId);
    }

    @Transactional
    @PostMapping("/orderMenu")
    public ResponseEntity<?> postOrder(@RequestBody OrderMenu.Request request) {
//        return orderMenuService.postOrder(request);

        return orderMenuService.post(request);
    }

    @Transactional
    @PatchMapping("/orderMenu")
    public ResponseEntity<?> patchOrder(@RequestBody OrderMenu.Request request) {
        return orderMenuService.patch(request);
    }

}
