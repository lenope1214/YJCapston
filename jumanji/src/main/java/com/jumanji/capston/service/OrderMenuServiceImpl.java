package com.jumanji.capston.service;

import com.jumanji.capston.data.*;
import com.jumanji.capston.repository.OrderMenuOptionRepository;
import com.jumanji.capston.repository.OrderMenuRepository;
import com.jumanji.capston.repository.UserRepository;
import com.jumanji.capston.service.exception.orderMenuException.OrderMenuNotFoundException;
import com.jumanji.capston.service.exception.shopException.ShopMissMatchException;
import com.jumanji.capston.service.interfaces.BasicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class OrderMenuServiceImpl implements BasicService<OrderMenu, OrderMenu.Request> {
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
    @Autowired
    OrderMenuOptionRepository orderMenuOptionRepository;
    @Autowired
    OptionServiceImpl optionService;


    public Set<OrderMenu> getOrderMenuByOrderId(Timestamp orderMenuId) {
        System.out.println("orderMenuId : " +orderMenuId.toString());
        Set<OrderMenu> orderList = orderMenuRepository.findByTab_IdStartsWith(orderMenuId.toString());
        return orderList;
    }


    @Override
    public OrderMenu get(@Nullable String authorization, String... str) {
        return null;
    }

    @Override
    public List<OrderMenu> getList(@Nullable String authorization, String... str) {
        return null;
    }

    @Override
    public OrderMenu post(@Nullable String authorization, OrderMenu.Request request) {
        return null;
    }

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

    public List<OrderMenu> post(String authorization, OrderMenu.RequestList requestList) {
        List<OrderMenu> response = new ArrayList<>();
        Menu menu;
        Tab table;
        for(OrderMenu.Request request : requestList.getList()){
            OrderMenu orderMenu;
            long orderId = request.getOrderId().getTime();
            String menuId = request.getMenuId();
            String tabId = request.getShopId() + request.getTabNo();

            orderService.isPresent(request.getOrderId());
            menu = menuService.isPresent(menuId);
            table = tableService.isPresent(tabId);

            int orderCount = orderMenuRepository.countByIdContains("" + orderId);
            String orderMenuId = orderId + "o" +String.format("%02d", orderCount);

            orderMenu = OrderMenu.builder()
                    .id(orderMenuId)
                    .quantity(request.getQuantity())
                    .menu(menu)
                    .tab(table)
                    .build();
            System.out.println(orderMenu.getTab().getId());
            orderMenuRepository.save(orderMenu); // order menu가 있어야지 option이 들어가므로 먼저 저장.

            if(request.getOptionList().size() > 0){
                List<OrderMenuOption.Request> optionList = request.getOptionList();
                List<OrderMenuOption> orderMenuOptionList = new ArrayList<>();
                for(OrderMenuOption.Request oMoption : optionList){
                    Option option = optionService.isPresent(oMoption.getOptionId());
                    OrderMenuOptionId id = OrderMenuOptionId.builder()
                            .orderMenu(orderMenu)
                            .option(option)
                            .build();
                    OrderMenuOption orderMenuOption =
                            OrderMenuOption.builder()
                            .id(id)
                            .quantity(oMoption.getQuantity())
                            .build();
                    orderMenuOptionList.add(orderMenuOption);
                    orderMenuOptionRepository.save(orderMenuOption);
                }
                orderMenu.setOptionList(orderMenuOptionList);
                System.out.println(orderMenu.toString());
            }
            response.add(orderMenu);
        }
        return response;
    }

    @Override
    public OrderMenu patch(String authorization, OrderMenu.Request request) {
        Order order;
        OrderMenu orderMenu;
        long orderId = request.getOrderId().getTime();
        String menuId = request.getMenuId();
        String tabId = request.getShopId() + request.getTabNo();
        Menu menu = null;
        Tab table = null;

        //유효성 검사
        isPresent(request.getOrderMenuId());
        orderMenu = orderMenuRepository.findById(request.getOrderMenuId()).get();
        menu = menuService.isPresent(menuId);
        table = tableService.isPresent(tabId);

        equalsShop(menuId.substring(0,10) , orderMenu.getMenu().getId().substring(0, 10));
        shopService.isPresent(menuId.substring(0,10));



        OrderMenu requestOrder = OrderMenu.builder()
                .quantity(request.getQuantity())
                .menu(menu)
                .tab(table)
                .build();
        orderMenu.patch(requestOrder);
        return orderMenu;
    }

    @Override
    public void delete(@Nullable String authorization, String... str) {
        String orderId = str[0];
    }

    public OrderMenu isPresent(String orderId) {
        Optional<OrderMenu> om = orderMenuRepository.findById(orderId);
        if (om.isPresent()) return om.get();
        throw new OrderMenuNotFoundException();
    }

    @Override
    public boolean isEmpty(String id) {
        return false;
    }

    public void equalsShop(String aId, String bId){
        if(aId.equals(bId))return ;
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
