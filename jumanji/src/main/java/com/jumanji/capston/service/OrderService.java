package com.jumanji.capston.service;

import com.jumanji.capston.controller.exception.OrderException.OrderNotFoundException;
import com.jumanji.capston.data.Order;
import com.jumanji.capston.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("error-3001", "없는 주문번호 입니다."));
    }


    public Order insert(Order.Request request) {
        Order order = null;
        order = Order.insertOrder()
                .id(orderRepository.getOrderSeqNextVal())
                .quantity(request.getQuantity())
                .orderRequest(request.getOrderRequest())
                .shop(shopRepository.findById(request.getShopId()).get())
                .user(userRepository.findById(request.getUserId()).get())
                .menu(menuRepository.findById(request.getMenuId()).get())
                .tab(tableRepository.findById(request.getTabId()).get())
                .build();
        return orderRepository.save(order);
    }

    public String delete(Order _order) {
        Order order = orderRepository.findById(_order.getId()).orElseThrow(() -> new IllegalArgumentException("id를 확인해주세요!!!"));
        orderRepository.delete(order);
        return "ok";
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
