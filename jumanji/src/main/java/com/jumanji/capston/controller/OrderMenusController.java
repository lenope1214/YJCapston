package com.jumanji.capston.controller;

import com.jumanji.capston.data.OrderMenu;
import com.jumanji.capston.service.OrderMenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class OrderMenusController{
    @Autowired
    OrderMenuServiceImpl orderMenuService;


    @Transactional(readOnly = true)
    @GetMapping("/order-menu/{orderMenuId}")
    public ResponseEntity<?> selectOrderByOrderId(@PathVariable Timestamp orderMenuId) {
        System.out.println("orderMenuId : " + orderMenuId);
        List<OrderMenu.Response> response = new ArrayList<>();
        Set<OrderMenu> orderMenuSet = orderMenuService.getOrderMenuByCartId(orderMenuId);
        System.out.println("주문메뉴 개수 : " + orderMenuSet.size());
        for(OrderMenu order : orderMenuSet){
            System.out.println("orderList info \n" +
                    "order.getId() : "+ order.getId() + "\n" +
                    "order.getMenuId : " + order.getMenu().getId().substring(10) +"\n" +
                    "order.get");
            response.add(new OrderMenu.Response(order));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/order-menu")
    public ResponseEntity<?> postOrder(@RequestHeader String authorization, @RequestBody OrderMenu.Request request) {
        OrderMenu orderMenu = orderMenuService.post(authorization, request);
        OrderMenu.Response response = new OrderMenu.Response(orderMenu);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Transactional
    @PatchMapping("/order-menu")
    public ResponseEntity<?> patchOrder(@RequestHeader String authorization, @RequestBody OrderMenu.Request request) {
        OrderMenu orderMenu= orderMenuService.patch(authorization, request);
        OrderMenu.Response response = new OrderMenu.Response(orderMenu);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
