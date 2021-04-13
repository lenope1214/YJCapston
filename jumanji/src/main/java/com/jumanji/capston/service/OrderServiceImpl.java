package com.jumanji.capston.service;

import com.jumanji.capston.data.Menu;
import com.jumanji.capston.data.Order;
import com.jumanji.capston.data.Tab;
import com.jumanji.capston.repository.OrderRepository;
import com.jumanji.capston.repository.UserRepository;
import com.jumanji.capston.service.exception.OrderException.OrderNotFoundException;
import com.jumanji.capston.service.exception.ShopException.ShopMissMatchException;
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
    ShopServiceImpl shopService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TableServiceImpl tableService;
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


        int orderCount = orderRepository.countByIdContains("" + cartId);
        String orderId = "" + cartId + String.format("%02d", orderCount);
        Menu menu = menuService.getMenuInfo(request.getMenuId());
        Tab table = tableService.getTableInfo(request.getTabId());
        order = Order.builder()
                .id(orderId)
                .quantity(request.getQuantity())
                .menu(menu)
                .tab(table)
                .build();
        System.out.println(order.getTab().getId());
        orderRepository.save(order);
        Order.Response response = new Order.Response(order);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> patch(Order.Request request) {
        Order order;
        Menu menu = null;
        Tab table = null;

        //유효성 검사
        isPresent(request.getOrderId());
        order = orderRepository.findById(request.getOrderId()).get();

        equalsShop(request.getMenuId().substring(0,10) , order.getMenu().getId().substring(0, 10));
        shopService.isPresent(request.getMenuId().substring(0,10));


        if(request.getMenuId() != null)menu = menuService.getMenuInfo(request.getMenuId());
        if(request.getTabId() != null)table = tableService.getTableInfo(request.getTabId());
        Order requestOrder = Order.builder()
                .quantity(request.getQuantity())
                .menu(menu)
                .tab(table)
                .build();
        order.patch(requestOrder);
        return new ResponseEntity<>(order, HttpStatus.OK);
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

    public void equalsShop(String beforeId, String afterId){
        if(beforeId.equals(afterId))return ;
        throw new ShopMissMatchException();
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
