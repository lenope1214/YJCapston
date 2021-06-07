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
@RequestMapping("/api/v1/")
public class OrderMenusController{
    @Autowired
    OrderMenuServiceImpl orderMenuService;


    @Transactional(readOnly = true)
    @GetMapping("order-menus/{orderId}")

    public ResponseEntity<?> selectOrderByOrderId(@PathVariable String orderId) {
        List<OrderMenu.Response> response = new ArrayList<>();
        Set<OrderMenu> orderMenuSet = orderMenuService.getOrderMenuByOrderId(orderId);
        for(OrderMenu orderMenu : orderMenuSet){
            response.add(new OrderMenu.Response(orderMenu));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("order-menus")
    public ResponseEntity<?> postOrder(@RequestHeader String authorization, @RequestBody OrderMenu.RequestList requestList) {
        List<OrderMenu> orderMenuList = orderMenuService.post(authorization, requestList);
        List<OrderMenu.Response> responseList= new ArrayList<>();
        for(OrderMenu orderMenu : orderMenuList){
            responseList.add(new OrderMenu.Response(orderMenu));
        }

        return new ResponseEntity<>(responseList, HttpStatus.CREATED);
    }

//    @PostMapping("order-menus-test")
//    public ResponseEntity<?> postOrderTest(@RequestHeader String authorization, @RequestBody OrderMenu.RequestList requestList) {
//
//        return new ResponseEntity<>(requestList.getList().get(0).getOptionList().get(0), HttpStatus.OK);
//    }
        @Transactional
    @PatchMapping("order-menus")
    public ResponseEntity<?> patchOrder(@RequestHeader String authorization, @RequestBody OrderMenu.Request request) {
        OrderMenu orderMenu= orderMenuService.patch(authorization, request);
        OrderMenu.Response response = new OrderMenu.Response(orderMenu);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
