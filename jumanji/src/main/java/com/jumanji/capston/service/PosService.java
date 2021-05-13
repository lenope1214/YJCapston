package com.jumanji.capston.service;

import com.jumanji.capston.data.*;
import com.jumanji.capston.repository.OrderMenuRepository;
import com.jumanji.capston.service.interfaces.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PosService implements BasicService<Pos, Pos.Request> {
    @Autowired
    OrderMenuRepository orderMenuRepository;
    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    TableServiceImpl tableService;




    @Override
    public Pos get(@Nullable String authorization, String... str) {
        return null;
    }

    @Override
    public List<Pos> getList(@Nullable String authorization, String... str) {
        String shopId = str[0];
        String tabNo;
        String orderRequest = null;
        int people = 0;
        List<OrderMenu> orderMenuList;

        List<Pos> posList = new ArrayList<>();
        List<String> orderTabIdList = orderMenuRepository.getTabListByShopId(shopId); // 해당 매장의 사용중인 테이블로 주문메뉴 검색

        List<Tab> shopTabList = tableService.getList(null, shopId);
        for(Tab shtab : shopTabList){
            for(String odtab : orderTabIdList){
                System.out.println("shtab : " + shtab +", odtab : " + odtab);
                if(shtab.getId().equals(odtab)){
                    Pos pos = new Pos();
                    tabNo = shtab.getId().substring(10);
                    Set<OrderMenu> orderMenuSet = orderMenuRepository.findByTab_IdStartsWith(odtab);
                    System.out.println("orderMenuSet>");
                    System.out.println("orderMenu Id.sub(0, 10) : " + orderMenuSet.iterator().next().getId().substring(0,13));
                    System.out.println("orderMenu Id.sub(0, 10) : " + new Timestamp(Long.parseLong(orderMenuSet.iterator().next().getId().substring(0,13))));
                    Order order = orderService.isPresent(new Timestamp(Long.parseLong(orderMenuSet.iterator().next().getId().substring(0,13))));
                    System.out.println("order info ->\n" +
                            "orderRequest : " + order.getOrderRequest() +"\n" +
                            "people : " + order.getPeople());
                    pos.setTabNo(tabNo);
                    pos.setOrderRequest(order.getOrderRequest());
                    pos.setPeople(order.getPeople());
                    pos.setOrderMenuSet(orderMenuSet);
                    posList.add(pos);
                    System.out.println(">>>>>>>>>>>>>>>>>");
                    break;
                }
            }
        }
        return posList;
    }

    @Override
    public Pos post(@Nullable String authorization, Pos.Request request) {
        return null;
    }

    @Override
    public Pos patch(@Nullable String authorization, Pos.Request request) {
        return null;
    }

    @Override
    public void delete(@Nullable String authorization, String... str) {

    }

    @Override
    public Pos isPresent(String id) {
        return null;
    }

    @Override
    public boolean isEmpty(String id) {
        return false;
    }
}
