package com.jumanji.capston.controller;

import com.jumanji.capston.data.Order;
import com.jumanji.capston.data.OrderMenu;
import com.jumanji.capston.repository.ReviewRepository;
import com.jumanji.capston.service.OrderMenuServiceImpl;
import com.jumanji.capston.service.OrderServiceImpl;
import com.jumanji.capston.service.UserServiceImpl;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class OrderController {
    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    OrderMenuServiceImpl orderMenuService;
    @Autowired
    ReviewRepository reviewRepository;

//    @Transactional(readOnly = true)
//    @GetMapping("/cartId")
//    public ResponseEntity<?> getCartId(){
//        return orderService.getCartId();
//    }

    @Transactional(readOnly = true)
    @GetMapping("orders") // My Order List
    public ResponseEntity<?> getOrderList(@RequestHeader String authorization){
        List<Order> orderList = orderService.getList(authorization);
        List<Order.Response> response = new ArrayList<>();
        for(Order order : orderList){
            response.add(new Order.Response(order));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("orders/list/{shopId}")
    public ResponseEntity<?> getOrderByShopId(@RequestHeader String authorization, @PathVariable String shopId){
        List<Order> orderList = orderService.getListByShopId(authorization, shopId);
        List<Order.Response> response = new ArrayList<>();

        for(Order order : orderList){
            response.add(new Order.Response(order));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("orders/{orderId}")
    public ResponseEntity<?> getOrder(@RequestHeader String authorization, @PathVariable String orderId){
        Long orderLong = Long.parseLong(orderId);
        Timestamp orderTime = new Timestamp(orderLong);
        List<OrderMenu> orderMenuList = orderMenuService.getList(authorization, orderTime);
        Order order =orderService.get(authorization,orderTime);
        Order.Response response = new Order.Response(order);
        List<OrderMenu.Response> omList = new ArrayList<>();
        omList.stream()
        response.setOrderMenuList();
//        for(OrderMenu orderMenu : orderMenuList){
//            response.add(new OrderMenu.Response(orderMenu));
//        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


//    @Transactional
//    @PostMapping("orders")
//    public ResponseEntity<?> postOrder(@RequestHeader String authorization, @RequestBody Order.Request request){
//        Order order = orderService.post(authorization, request);
//        Order.Response response = new Order.Response(order);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }

    @RequestMapping(value = "orders", method = {RequestMethod.POST, RequestMethod.PATCH})
    public ResponseEntity<?> postOrder(@RequestHeader String authorization, @RequestBody Order.Request request){
        Order order = orderService.patch(authorization, request);
        Order.Response response = new Order.Response(order);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("orders/accept")
    public ResponseEntity<?> orderAccept(@RequestHeader String authorization, @RequestBody Order.Request request){
        Order order = orderService.orderAccept(authorization, request);
        Order.Response response = new Order.Response(order);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("orders/admin")
    public ResponseEntity<?> getOrderAll(){
        return new ResponseEntity<>(orderService.getList(), HttpStatus.OK);
    }

//    @Transactional
//    @DeleteMapping("/order/{orderId}")
//    public ResponseEntity<?> deleteOrder(@RequestHeader String authorization, @PathVariable Timestamp orderId){
//        orderService.delete(authorization, orderId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}