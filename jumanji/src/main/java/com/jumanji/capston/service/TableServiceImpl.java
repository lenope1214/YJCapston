package com.jumanji.capston.service;

import com.jumanji.capston.data.Order;
import com.jumanji.capston.data.Tab;
import com.jumanji.capston.repository.TableRepository;
import com.jumanji.capston.service.exception.tableException.TableHasExistException;
import com.jumanji.capston.service.exception.tableException.TableNotFoundException;
import com.jumanji.capston.service.interfaces.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class TableServiceImpl implements BasicService<Tab, Tab.Request, String> {
    @Autowired
    TableRepository tableRepository;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ShopServiceImpl shopService;
    @Autowired
    OrderServiceImpl orderService;


    @Override
    public Tab get(@Nullable String authorization, String... tableId) {
        Tab tab = isPresent(tableId[0]);
        return tab;
    }

    @Override
    public List<Tab> getList(@Nullable String authorization, String... shopId) {
        // 유효성 체크
        shopService.isPresent(shopId[0]);
        return tableRepository.findByIdContains(shopId[0]);
    }

    @Override
    public Tab post(String authorization, Tab.Request request) {
        String loginId = userService.getMyId(authorization);
        String tabId = request.getShopId() + String.format("%02d", request.getNo());
        System.out.println("request.getNo() : " + request.getNo());
        System.out.println("tabId : " + tabId);
        // 유효성 체크 --
        userService.isPresent(loginId); // 로그인 아이디가 존재하는지
        shopService.isOwnShop(loginId, request.getShopId()); // 내 매장이 맞는지
        isEmpty(tabId); // 전에 만들어둔 테이블과 겹치는지

        Tab tab = Tab.builder()
                .tabId(tabId)
                .seatQty(request.getSeatQty())
                .qrCode(request.getQrCode())
                .build();
        tableRepository.save(tab);
        return tab;
    }

    @Override
    public Tab patch(String authorization, Tab.Request request) {
        String tabId = toTabId(request.getShopId(), request.getNo());
        String loginId = userService.getMyId(authorization);
        Order order = null;
        // 유효성 검사
        isOwnTable(loginId, request.getShopId(), request.getNo());
        order = orderService.isPresent(new Timestamp(Long.parseLong(request.getOrderId())));
        Tab tab = isPresent(tabId); // 존재하는 테이블인지 확인
        tab.update(request);
        if(order != null)tab.setOrder(order);
        System.out.println("테이블 변경!!!! 정보 : " + tab.toString());
        tableRepository.saveAndFlush(tab);

        return tab;
    }

//    public Tab updateUsing(String authorization, Tab.Request request) {
//        String tabId = toTabId(request.getShopId(), request.getNo());
//        String loginId = userService.getMyId(authorization);
//
//        // 유효성 검사
//        isOwnTable(loginId, request.getShopId(), request.getNo());
//
//        Tab tab = tableRepository.findById(tabId).get();
//        tab.update(request);
//        tableRepository.saveAndFlush(tab);
//        return tab;
//    }

    @Override
    public void delete(@Nullable String authorization, String... str) {
        String tableId = str[0];
        String loginId = userService.getMyId(authorization);
        isOwnTable(loginId, tableId.substring(0, 10), Integer.parseInt(tableId.substring(10)));
        Tab tab = tableRepository.findById(tableId).get();
        tableRepository.delete(tab);
    }

    @Override
    public Tab isPresent(String id) {
        Optional<Tab> tab = tableRepository.findById(id);
        if (tab.isPresent()) return tab.get();
        throw new TableNotFoundException();
    }

    @Override
    public boolean isEmpty(String id) {
        if (tableRepository.findById(id).isEmpty()) return true;
        throw new TableHasExistException();
    }

    public void isOwnTable(String loginId, String shopId, int tabNo) {
        String tabId = toTabId(shopId, tabNo);
        userService.isPresent(loginId); // 존재하는 아이디인지 확인
        shopService.isPresent(shopId);
//        shopService.isOwnShop(loginId, shopId); // 내 매장인지 확인
    }

    public String toTabId(String shopId, int tabNo) {
        return shopId + String.format("%02d", tabNo);
    }
}
