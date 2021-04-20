package com.jumanji.capston.service;

import com.jumanji.capston.data.Tab;
import com.jumanji.capston.repository.TableRepository;
import com.jumanji.capston.service.exception.TableException.TableHasExistException;
import com.jumanji.capston.service.exception.TableException.TableNotFoundException;
import com.jumanji.capston.service.interfaces.BasicService;
import com.jumanji.capston.service.interfaces.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TableServiceImpl implements TableService, BasicService {
    @Autowired
    TableRepository tableRepository;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ShopServiceImpl shopService;

    public Tab getTableInfo(String tabId) {
        isPresent(tabId);
        Tab table = tableRepository.findById(tabId).get();
        return table;
    }


    @Override
    public Tab.Response get(String tableId) {
        isPresent(tableId);
        Tab tab = tableRepository.findById(tableId).get();
        Tab.Response response = new Tab.Response(tab);
        return response;
    }

    @Override
    public List<Tab.Response> getList(String shopId) {
        // 유효성 체크
        shopService.isPresent(shopId);
        List<Tab.Response> response = new ArrayList<>();
        for(Tab tab : tableRepository.findByIdContains(shopId)){
            response.add(new Tab.Response(tab));
        }
        return response;
    }

    @Override
    public Tab.Response post(String authorization, Tab.Request request) {
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
        Tab.Response response = new Tab.Response(tableRepository.save(tab));
        return response;
    }

    @Override
    public Tab.Response patch(String authorization, Tab.Request request) {
        String tabId = toTabId(request.getShopId(), request.getNo());
        String loginId = userService.getMyId(authorization);

        // 유효성 검사
        isOwnTable(loginId, request.getShopId(), request.getNo());

        Tab tab = tableRepository.findById(tabId).get();
        tab.update(request);
        return null;
    }

    @Override
    public void delete(String authorization, String tableId) {
        String loginId = userService.getMyId(authorization);
        isOwnTable(loginId, tableId.substring(0, 10), Integer.parseInt(tableId.substring(10)));
        Tab tab = tableRepository.findById(tableId).get();
        tableRepository.delete(tab);
    }

    @Override
    public boolean isPresent(String id) {
        if (tableRepository.findById(id).isPresent()) return true;
        throw new TableNotFoundException();
    }

    @Override
    public boolean isEmpty(String id) {
        if (tableRepository.findById(id).isEmpty()) return true;
        throw new TableHasExistException();
    }

    public void isOwnTable(String loginId, String shopId, int tabNo){
        String tabId = toTabId(shopId, tabNo);
        userService.isPresent(loginId); // 존재하는 아이디인지 확인
        shopService.isPresent(shopId);
        shopService.isOwnShop(loginId, shopId); // 내 매장인지 확인
        isPresent(tabId); // 존재하는 테이블인지 확인
    }

    public String toTabId(String shopId, int tabNo){
        return shopId + String.format("%02d", tabNo);
    }
}
