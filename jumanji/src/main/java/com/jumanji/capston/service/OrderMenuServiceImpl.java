package com.jumanji.capston.service;

import com.jumanji.capston.data.Menu;
import com.jumanji.capston.data.Order;
import com.jumanji.capston.data.OrderMenu;
import com.jumanji.capston.data.Tab;
import com.jumanji.capston.repository.OrderMenuRepository;
import com.jumanji.capston.repository.UserRepository;
import com.jumanji.capston.service.exception.orderMenuException.OrderMenuNotFoundException;
import com.jumanji.capston.service.exception.shopException.ShopMissMatchException;
import com.jumanji.capston.service.interfaces.BasicService;
import com.jumanji.capston.service.interfaces.OrderMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class OrderMenuServiceImpl implements OrderMenuService, BasicService {
    @Autowired
    OrderMenuRepository orderMenuRepository;
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
    @Autowired
    UserServiceImpl userService;


    public Set<OrderMenu> getOrderMenuByOrderId(Timestamp orderMenuId) {
        System.out.println("orderMenuId : " +orderMenuId.toString());
        Set<OrderMenu> orderList = orderMenuRepository.findByTab_IdStartsWith(orderMenuId.toString());
        return orderList;
    }


    @Override
    public OrderMenu get(String authorization, String orderMenuId) {
        return null;
    }

    @Override
    public List<OrderMenu> getList(String authorization, Timestamp orderId) {
        userService.isLogin(authorization);
        orderService.isPresent(orderId);

        List<OrderMenu> orderMenuList;
        System.out.println("orderId.toStrig : " + orderId.getTime());
        orderMenuList = orderMenuRepository.getOrderMenuList("" + orderId.getTime());
        return orderMenuList;
    }

    public List<OrderMenu> getList(String shopId) {
        List<OrderMenu> orderMenuList;

        return null;
    }

    @Override
    public List<OrderMenu> post(String authorization, OrderMenu.RequestList requestList) {
        List<OrderMenu> response = new ArrayList<>();
        for(OrderMenu.Request request : requestList.getList()){
            OrderMenu orderMenu;
            long orderId = request.getOrderId().getTime();
            String menuId = request.getShopId() + request.getMenuName();
            String tabId = request.getShopId() + request.getTabNo();

            orderService.isPresent(request.getOrderId());
            menuService.isPresent(menuId);
            tableService.isPresent(tabId);

            int orderCount = orderMenuRepository.countByIdContains("" + orderId);
            String orderMenuId = orderId + "o" +String.format("%02d", orderCount);
            Menu menu = menuService.getMenuInfo(menuId);
            Tab table = tableService.getTableInfo(tabId);
            orderMenu = OrderMenu.builder()
                    .id(orderMenuId)
                    .quantity(request.getQuantity())
                    .menu(menu)
                    .tab(table)
                    .build();
            System.out.println(orderMenu.getTab().getId());
            response.add(orderMenuRepository.save(orderMenu));
        }
        return response;
    }

    @Override
    public OrderMenu patch(String authorization, OrderMenu.Request request) {
        Order order;
        OrderMenu orderMenu;
        long orderId = request.getOrderId().getTime();
        String menuId = request.getShopId() + request.getMenuName();
        String tabId = request.getShopId() + request.getTabNo();
        Menu menu = null;
        Tab table = null;

        //유효성 검사
        isPresent(request.getOrderMenuId());
        orderMenu = orderMenuRepository.findById(request.getOrderMenuId()).get();

        equalsShop(menuId.substring(0,10) , orderMenu.getMenu().getId().substring(0, 10));
        shopService.isPresent(menuId.substring(0,10));


        if(menuId != null)menu = menuService.getMenuInfo(menuId);
        if(tabId != null)table = tableService.getTableInfo(tabId);
        OrderMenu requestOrder = OrderMenu.builder()
                .quantity(request.getQuantity())
                .menu(menu)
                .tab(table)
                .build();
        orderMenu.patch(requestOrder);
        return orderMenu;
    }


    @Override
    public void delete(String authorization, String orderId) {
    }

    public Object isPresent(String orderId) {
        if (orderMenuRepository.findById(orderId).isPresent()) return true;
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
