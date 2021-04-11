package com.jumanji.capston.service;

import com.jumanji.capston.data.Cart;
import com.jumanji.capston.data.Shop;
import com.jumanji.capston.data.User;
import com.jumanji.capston.repository.CartRepository;
import com.jumanji.capston.service.exception.CartException.CartHasExistException;
import com.jumanji.capston.service.exception.CartException.CartNotFoundException;
import com.jumanji.capston.service.interfaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;


@Service
public class CartServiceImpl implements CartService, BasicService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ShopServiceImpl shopService;

    @Override
    public ResponseEntity<?> get(String cartId) {
        isPresent(cartId);
        Cart cart = cartRepository.findById(cartId).get();
        Cart.Response response =
                new Cart.Response(cart);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getList(String userId) {
        for(Cart cart : cartRepository.findALLByUser_Id(userId)){
            System.out.println("출력");
        }
        return new ResponseEntity<>(cartRepository.findALLByUser_Id(userId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> post(Cart.Request request) {
        Cart cart;
        //isEmpty 는 필요 x 주문은 매번 새로운 애기 때문.
        System.out.println("매장번호의 매장이 있나요? " + shopService.isPresent(request.getShopId()));
        System.out.println("유저번호의 유저가 있나요? " + userService.isPresent(request.getUserId()));
        Shop shop = shopService.isPresent(request.getShopId()) ?
                shopService.shopRepository.findById(request.getShopId()).get() : null;
        User user = userService.isPresent(request.getUserId()) ?
                userService.getUserInfo(request.getUserId()): null;
        System.out.println("가져온 매장번호 : " + shop.getId());
        System.out.println("가져온 유저번호 : " + user.getId());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Timestamp requestTime = new Timestamp(System.currentTimeMillis());
        String cartId = dateFormat.format(requestTime);
        cart = Cart.builder()
                .id(cartId)
                .orderRequest(request.getOrderRequest())
                .quantity(request.getQuantity())
                .shop(shop)
                .user(user)
                .build();
        cartRepository.save(cart);
        Cart.Response response = new Cart.Response(cart);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> patch(Cart.Request request) {
        return null; // 주문수정은 .. 없어도 되지 않을까?
    }

    @Override
    public ResponseEntity<?> delete(String cartId) {
        System.out.println("");
        isPresent(cartId);
        Cart cart = cartRepository.findById(cartId).get();
        cartRepository.delete(cart);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public boolean isPresent(String id) {
        if (cartRepository.findById(id).isPresent()) return true;
        throw new CartNotFoundException(id);
    }

    @Override
    public boolean isEmpty(String id) {
        if (cartRepository.findById(id).isEmpty()) return true;
        throw new CartHasExistException();
    }

}
