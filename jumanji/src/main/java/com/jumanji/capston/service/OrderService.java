package com.jumanji.capston.service;

import com.jumanji.capston.controller.exception.OrderException.OrderNotFoundException;
import com.jumanji.capston.data.Order;
import com.jumanji.capston.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ShopRepository shopRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TableRepository tableRepository;
    @Autowired
    MenuRepository menuRepository;


    public Order insert(Order.Request request) {
        Order order = null;
        return null;
//        order = Order.insertOrder()
//                .id(orderRepository.getOrderSeqNextVal())
//                .quantity(request.getQuantity())
//                .orderRequest(request.getOrderRequest())
//                .shop(shopRepository.findById(request.getShopId()).get())
//                .user(userRepository.findById(request.getUserId()).get())
//                .menu(menuRepository.findById(request.getMenuId()).get())
//                .tab(tableRepository.findById(request.getTabId()).get())
//                .build();
//        return orderRepository.save(order);
    }

    public String delete(Order order) {
        Order orderEntity = orderRepository.findById(order.getId()).orElseThrow(() -> new IllegalArgumentException("id를 확인해주세요!!!"));
        orderRepository.delete(orderEntity);
        return "ok";
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public ResponseEntity<?> getOrderByOrderId(String orderId) {

        return new ResponseEntity<>(orderRepository.findById(orderId), HttpStatus.OK);
    }

    public boolean isPresent(String orderId){
        if(orderRepository.findById(orderId).isPresent())return true;
        throw new OrderNotFoundException();
    }

//    public ResponseEntity<?> postOrder(Order.Request request) {
//        Order order;
//        Order.builder()
//                .id(orderRepository.getOrderSeqNextVal())
//                .orderRequest(request.getOrderRequest())
//                .quantity(request.getQuantity())
//                .tab()
//
//    }
}
