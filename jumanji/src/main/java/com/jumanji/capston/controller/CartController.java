package com.jumanji.capston.controller;

import com.jumanji.capston.data.Cart;
import com.jumanji.capston.service.CartServiceImpl;
import com.jumanji.capston.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/api/v1")
public class CartController {
    @Autowired
    CartServiceImpl cartService;
    @Autowired
    UserServiceImpl userService;


    @Transactional(readOnly = true)
    @GetMapping("/cart/{cartId}")
    public ResponseEntity<?> getCart(@PathVariable Timestamp cartId){
        return cartService.get(cartId);
    }

    @Transactional(readOnly = true)
    @GetMapping("/cart/myCartList")
    public ResponseEntity<?> getCartList(@RequestHeader String authorization){
        String loginId = userService.getMyId(authorization);
        return cartService.getList(loginId);
    }

    @Transactional
    @PostMapping("/cart")
    public ResponseEntity<?> postCart(@RequestBody Cart.Request request){
//        System.out.println("request info \n" + request.getQuantity() +"\n" + request.getOrderRequest() +"\n" + request.getShopId() +"\n" + request.getUserId());
        return cartService.post(request);
    }
}