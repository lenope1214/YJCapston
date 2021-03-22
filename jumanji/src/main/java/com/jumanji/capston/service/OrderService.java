package com.jumanji.capston.service;

import com.jumanji.capston.data.Order;
import com.jumanji.capston.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id를 확인해주세요!!!"));
    }


    public Order insert(Order _order) {
        return orderRepository.save(_order);
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
