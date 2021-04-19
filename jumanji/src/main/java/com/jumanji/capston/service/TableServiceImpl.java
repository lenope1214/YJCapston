package com.jumanji.capston.service;

import com.jumanji.capston.data.Shop;
import com.jumanji.capston.data.Tab;
import com.jumanji.capston.repository.TableRepository;
import com.jumanji.capston.service.exception.TableException.TableHasExistException;
import com.jumanji.capston.service.exception.TableException.TableNotFoundException;
import com.jumanji.capston.service.interfaces.BasicService;
import com.jumanji.capston.service.interfaces.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public Tab.Response post(String authorization, Tab.Request request) {
        String loginId = userService.getMyId(authorization);
        String tabId = request.getShopId() + String.format("%02d", request.getNo());

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
    public Tab.Response patch(Tab.Request request) {
        return null;
    }

    @Override
    public boolean delete(String tableId) {
        return false;
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
}
