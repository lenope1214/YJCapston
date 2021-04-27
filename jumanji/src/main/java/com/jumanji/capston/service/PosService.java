package com.jumanji.capston.service;

import com.jumanji.capston.data.*;
import com.jumanji.capston.repository.OrderMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PosService {
    @Autowired
    OrderMenuRepository orderMenuRepository;
    @Autowired
    OrderServiceImpl orderService;


    public List<Pos> getShopPos(String shopId){
        List<Pos> posList = new ArrayList<>();
        List<Tab> tabList = orderMenuRepository.getTabListByShopId(shopId); // 해당 매장의 사용중인 테이블로 주문메뉴 검색
        List<Order> orderList = orderService.getListByShopId(shopId);

        for(Tab tab : tabList){
            Order order = orderService.getOrderInfo(DateOperator.strToTimestamp(tab.getId().substring(0, 13)));
            String tabNo = orderMenu.getTab().getId().substring(10);
            String orderRequest = order.getOrderRequest();
            int people = order.getPeople();
            List<>
            posList.add(Pos.builder()
                    .tabNo(tabNo)
                    .orderRequest(orderRequest)
                    .people(people)
                    .orderMenuList()
                    .build());
        }

        return posList;
    }
}
