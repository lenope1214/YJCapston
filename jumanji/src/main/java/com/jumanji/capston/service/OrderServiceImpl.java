package com.jumanji.capston.service;

import com.jumanji.capston.data.Order;
import com.jumanji.capston.data.Shop;
import com.jumanji.capston.data.User;
import com.jumanji.capston.repository.OrderRepository;
import com.jumanji.capston.service.exception.OrderException.OrderHasExistException;
import com.jumanji.capston.service.exception.OrderException.OrderNotFoundException;
import com.jumanji.capston.service.interfaces.OrderService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ShopServiceImpl shopService;

//    public ResponseEntity<?> getCartId() {
//        @Getter
//        @Setter
//        class Response {
//            private Timestamp cartId;
//        }
//        Response response = new Response();
//        response.setCartId(new Timestamp(System.currentTimeMillis()));
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    public Order getOrderInfo(Timestamp orderId){
        return orderRepository.findById(orderId).get();
    }

    @Override
    public ResponseEntity<?> get(Timestamp orderId) {
        isPresent(orderId);
//        Timestamp cartIdTime = DateOperator.stringToTimestamp(cartId);
        Order cart = orderRepository.findById(orderId).get();
        Order.Response response =
                new Order.Response(cart);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getList(String userId) {
        for (Order cart : orderRepository.findALLByUser_Id(userId)) {
            System.out.println("출력");
        }
        return new ResponseEntity<>(orderRepository.findALLByUser_Id(userId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> post(String authorization, Order.Request request) {
        Order order;
        Shop shop;
        User user;
        String loginId = userService.getMyId(authorization);
        userService.isPresent(loginId);
        shopService.isPresent(request.getShopId());

        //isEmpty 는 필요 x 주문은 매번 새로운 애기 때문.
        shop = shopService.getShopInfo(request.getShopId());
        user = userService.getUserInfo(loginId);

        order = Order.builder()
                .id(new Timestamp(System.currentTimeMillis()))
//                .orderRequest(request.getOrderRequest()) // 얘와 밑의 얘네 둘은 결제 완료 후에 들어갈 예정
//                .people(request.getPeople())
                .shop(shop)
                .user(user)
                .build();
        orderRepository.save(order);
        Order.Response response = new Order.Response(order);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> patch(String authorization, Order.Request request) {
        Order order;
        System.out.println("request info \nrequest.getOrderId()" + request.getOrderId()+"\n" +
                "request.getPeople" + request.getPeople() + "\n" +
                "request.getOrderRequest" + request.getOrderRequest());
        String loginId = userService.getMyId(authorization);
        userService.isPresent(loginId);

        //isEmpty 는 필요 x 주문은 매번 새로운 애기 때문.
        order = getOrderInfo(request.getOrderId());
        order.update(request);
        orderRepository.save(order);
        Order.Response response = new Order.Response(order);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Timestamp cartId) {
        System.out.println("");
        isPresent(cartId);
        Order cart = orderRepository.findById(cartId).get();
        orderRepository.delete(cart);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public boolean isPresent(Timestamp id) {
        if (orderRepository.findById(id).isPresent()) return true;
        throw new OrderNotFoundException();
    }

    public boolean isEmpty(Timestamp id) {
        if (orderRepository.findById(id).isEmpty()) return true;
        throw new OrderHasExistException();
    }

}
