package com.jumanji.capston.service;

import com.jumanji.capston.data.Order;
import com.jumanji.capston.repository.*;
import com.jumanji.capston.service.exception.OrderException.OrderNotFoundException;
import com.jumanji.capston.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService, BasicService {
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
    @Autowired
    CartServiceImpl cartService;
    @Autowired
    MenuServiceImpl menuService;

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


    @Override
    public ResponseEntity<?> get(String orderId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getList(String cartId) {
        return null;
    }

    @Override
    public ResponseEntity<?> post(Order.Request request) {
        System.out.println("post order request info \n" +
                "cartId : " + request.getCartId() + "\n" +
                "menuId : " + request.getMenuId() + "\n" +
                "quantity : " + request.getQuantity() + "\n" +
                "tabId : " + request.getTabId()
        );
        Order order = null;
        long cartId = request.getCartId().getTime();
        cartService.isPresent(request.getCartId());
        menuService.isPresent(request.getMenuId());


        System.out.println("비교 cartId : " + cartId);
        int orderCount = orderRepository.countByIdContains("" + cartId);
        System.out.println("카트의 주문 수 : " + orderCount);
        String orderId = "" + cartId + String.format("%02d", orderCount);
        System.out.println("가져올 테이블 id : " + request.getTabId());
        order = Order.builder()
                .id(orderId)
                .quantity(request.getQuantity())
                .menu(menuRepository.findById(request.getMenuId()).get())
                .tab(tableRepository.findById(request.getTabId()).get())
                .build();
        orderRepository.save(order);
        Order.Response response = new Order.Response(order);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> patch(Order.Request request) {
        return null;
    }


    @Override
    public ResponseEntity<?> delete(String orderId) {
        return null;
    }

    public boolean isPresent(String orderId) {
        if (orderRepository.findById(orderId).isPresent()) return true;
        throw new OrderNotFoundException();
    }

    @Override
    public boolean isEmpty(String id) {
        return false;
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
