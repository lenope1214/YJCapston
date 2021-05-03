package com.jumanji.capston.service;

import com.jumanji.capston.data.*;
import com.jumanji.capston.repository.OrderRepository;
import com.jumanji.capston.service.exception.OrderException.OrderHasExistException;
import com.jumanji.capston.service.exception.OrderException.OrderNotFoundException;
import com.jumanji.capston.service.interfaces.BasicService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
        isPresent(orderId);
        return orderRepository.findById(orderId).get();
    }

    @Override
    public Order get(String authorization, Timestamp orderId) {
        String loginId = userService.getMyId(authorization);

        Order order = isPresent(orderId);
        return order;
    }

    public List<Order> getList(String authorization) {
        String loginId = userService.getMyId(authorization);
        List<Order> orderList = new ArrayList<>();
        for (Order order : orderRepository.findALLByUser_Id(loginId)) {
            orderList.add(order);
        }
        return orderList;
    }

    @Override
    public List<Order> getList(String authorization, String userId) {
        String loginId = userService.getMyId(authorization);
        User user = userService.get(loginId);
        userService.isAuth(user.getRole(), "ADMIN");

        List<Order> orderList = new ArrayList<>();
        for (Order order : orderRepository.findALLByUser_Id(userId)) {
            orderList.add(order);
        }
        return orderList;
    }

    @Override
    public Order post(String authorization, Order.Request request) {
        Order order;
        Shop shop;
        User user;
        String loginId = userService.getMyId(authorization);
        userService.isPresent(loginId);
        shopService.isPresent(request.getShopId());

        //isEmpty 는 필요 x 주문은 매번 새로운 애기 때문.
        shop = shopService.get(request.getShopId());
        user = userService.get(loginId);

        order = Order.builder()
                .id(new Timestamp(System.currentTimeMillis()))
//                .orderRequest(request.getOrderRequest()) // 얘와 밑의 얘네 둘은 결제 완료 후에 들어갈 예정
//                .people(request.getPeople())
                .shop(shop)
                .user(user)
                .build();
                orderRepository.save(order);
        return order;
    }

    @Override
    public Order patch(String authorization, Order.Request request) {
        Order order;
        System.out.println("request info \nrequest.getOrderId()" + request.getOrderId() + "\n" +
                "request.getPeople" + request.getPeople() + "\n" +
                "request.getOrderRequest" + request.getOrderRequest());
        String loginId = userService.getMyId(authorization);

        // 유효성 검사.
        userService.isPresent(loginId);
        isPresent(request.getOrderId()); // 있는지~

        order = getOrderInfo(request.getOrderId());
        order.update(request);
        orderRepository.save(order);
        return order;
    }

//    @Override
//    public void delete(String authorization, Timestamp orderId) {
//        isPresent(orderId);
//        Order order = orderRepository.findById(orderId).get();
//        orderRepository.delete(order);
//    }

    public Order isPresent(Timestamp orderId) {
        Optional<Order> order =orderRepository.findById(orderId);
        if (order.isPresent()) return order.get();
        throw new OrderNotFoundException();
    }

    public boolean isEmpty(Timestamp orderId) {
        if (orderRepository.findById(orderId).isEmpty()) return true;
        throw new OrderHasExistException();
    }

    public Order isOwnOrder(Timestamp orderId, String userId){

        Order order = isPresent(orderId);
        if(order.getUser().getId().equals(userId))return order;
        else throw new OrderNotFoundException(""+orderId.getTime());
    }

    public List<Order> getListByShopId(String shopId) {
        List<Order> orderList;
        orderList = orderRepository.findAllByShop_Id(shopId);
        System.out.println("해당 매장의 주문목록");
        for(Order order: orderList){
            System.out.println("getOrderRequest : " + order.getOrderRequest() + "\n");
            System.out.println("getUser().getName()" + order.getUser().getName());
        }
        return null;
    }

    public void statusUpdate(Order order) {
        orderRepository.save(order);
    }
}
