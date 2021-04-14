package com.jumanji.capston.service;

import com.jumanji.capston.data.Menu;
import com.jumanji.capston.data.OrderMenu;
import com.jumanji.capston.data.Tab;
import com.jumanji.capston.repository.OrderMenuRepository;
import com.jumanji.capston.repository.UserRepository;
import com.jumanji.capston.service.exception.OrderMenuException.OrderMenuNotFoundException;
import com.jumanji.capston.service.exception.ShopException.ShopMissMatchException;
import com.jumanji.capston.service.interfaces.BasicService;
import com.jumanji.capston.service.interfaces.OrderMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrderMenuServiceImpl implements OrderMenuService, BasicService {
    @Autowired
    OrderMenuRepository orderRepository;
    @Autowired
    ShopServiceImpl shopService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TableServiceImpl tableService;
    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    MenuServiceImpl menuService;

    public String delete(OrderMenu order) {
        OrderMenu orderEntity = orderRepository.findById(order.getId()).orElseThrow(() -> new IllegalArgumentException("id를 확인해주세요!!!"));
        orderRepository.delete(orderEntity);
        return "ok";
    }


    public ResponseEntity<?> getOrderMenuByCartId(Timestamp orderMenuId) {
        System.out.println("orderMenuId : " +orderMenuId.toString());
        Set<OrderMenu> orderList = orderRepository.findByIdContains(orderMenuId.toString());
        List<OrderMenu.Response> response = new ArrayList<>();
        System.out.println("주문메뉴 개수 : " + orderList.size());
        for(OrderMenu order : orderRepository.findByIdContains(orderMenuId.toString())){
            System.out.println("orderList info \n" +
                    "order.getId() : "+ order.getId() + "\n" +
                    "order.getMenuId : " + order.getMenu().getId().substring(10) +"\n" +
                    "order.get");
            response.add(new OrderMenu.Response(order));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
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
    public ResponseEntity<?> post(OrderMenu.Request request) {
        System.out.println("post order request info \n" +
                "orderId : " + request.getOrderId() + "\n" +
                "menuId : " + request.getMenuId() + "\n" +
                "quantity : " + request.getQuantity() + "\n" +
                "tabId : " + request.getTabId()
        );
        OrderMenu orderMenu = null;
        long cartId = request.getOrderId().getTime();
        orderService.isPresent(request.getOrderId());
        menuService.isPresent(request.getMenuId());


        int orderCount = orderRepository.countByIdContains("" + cartId);
        String orderMenuId = "" + cartId + String.format("%02d", orderCount);
        Menu menu = menuService.getMenuInfo(request.getMenuId());
        Tab table = tableService.getTableInfo(request.getTabId());
        orderMenu = OrderMenu.builder()
                .id(orderMenuId)
                .quantity(request.getQuantity())
                .menu(menu)
                .tab(table)
                .build();
        System.out.println(orderMenu.getTab().getId());
        orderRepository.save(orderMenu);
        OrderMenu.Response response = new OrderMenu.Response(orderMenu);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> patch(OrderMenu.Request request) {
        OrderMenu order;
        Menu menu = null;
        Tab table = null;

        //유효성 검사
        isPresent(request.getOrderMenuId());
        order = orderRepository.findById(request.getOrderMenuId()).get();

        equalsShop(request.getMenuId().substring(0,10) , order.getMenu().getId().substring(0, 10));
        shopService.isPresent(request.getMenuId().substring(0,10));


        if(request.getMenuId() != null)menu = menuService.getMenuInfo(request.getMenuId());
        if(request.getTabId() != null)table = tableService.getTableInfo(request.getTabId());
        OrderMenu requestOrder = OrderMenu.builder()
                .quantity(request.getQuantity())
                .menu(menu)
                .tab(table)
                .build();
        order.patch(requestOrder);
        OrderMenu.Response response = new OrderMenu.Response(order);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<?> delete(String orderId) {
        return null;
    }

    public boolean isPresent(String orderId) {
        if (orderRepository.findById(orderId).isPresent()) return true;
        throw new OrderMenuNotFoundException();
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
