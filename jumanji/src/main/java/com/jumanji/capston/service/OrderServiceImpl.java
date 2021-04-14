package com.jumanji.capston.service;

import com.jumanji.capston.data.Order;
import com.jumanji.capston.data.Shop;
import com.jumanji.capston.data.User;
import com.jumanji.capston.repository.OrderRepository;
import com.jumanji.capston.service.exception.OrderException.OrderHasExistException;
import com.jumanji.capston.service.exception.OrderException.OrderNotFoundException;
import com.jumanji.capston.service.interfaces.CartService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;


@Service
public class OrderServiceImpl implements CartService {
    @Autowired
    OrderRepository cartRepository;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ShopServiceImpl shopService;

    public ResponseEntity<?> getCartId() {
        @Getter
        @Setter
        class Response {
            private Timestamp cartId;
        }
        Response response = new Response();
        response.setCartId(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> get(Timestamp cartId) {
        isPresent(cartId);
//        Timestamp cartIdTime = DateOperator.stringToTimestamp(cartId);
        Order cart = cartRepository.findById(cartId).get();
        Order.Response response =
                new Order.Response(cart);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getList(String userId) {
        for (Order cart : cartRepository.findALLByUser_Id(userId)) {
            System.out.println("출력");
        }
        return new ResponseEntity<>(cartRepository.findALLByUser_Id(userId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> post(Order.Request request) {
        Order cart;
        //isEmpty 는 필요 x 주문은 매번 새로운 애기 때문.
        System.out.println("매장번호의 매장이 있나요? " + shopService.isPresent(request.getShopId()));
        System.out.println("유저번호의 유저가 있나요? " + userService.isPresent(request.getUserId()));
        Shop shop = shopService.isPresent(request.getShopId()) ?
                shopService.shopRepository.findById(request.getShopId()).get() : null;
        User user = userService.isPresent(request.getUserId()) ?
                userService.getUserInfo(request.getUserId()) : null;
        System.out.println("가져온 매장번호 : " + shop.getId());
        System.out.println("가져온 유저번호 : " + user.getId());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        cart = Order.builder()
                .id(request.getCartId())
                .orderRequest(request.getOrderRequest())
                .shop(shop)
                .user(user)
                .build();
        cartRepository.save(cart);
        Order.Response response = new Order.Response(cart);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> patch(Order.Request request) {
        return null; // 주문수정은 .. 없어도 되지 않을까?
    }

    @Override
    public ResponseEntity<?> delete(Timestamp cartId) {
        System.out.println("");
        isPresent(cartId);
        Order cart = cartRepository.findById(cartId).get();
        cartRepository.delete(cart);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public boolean isPresent(Timestamp id) {
        if (cartRepository.findById(id).isPresent()) return true;
        throw new OrderNotFoundException();
    }

    public boolean isEmpty(Timestamp id) {
        if (cartRepository.findById(id).isEmpty()) return true;
        throw new OrderHasExistException();
    }

}
