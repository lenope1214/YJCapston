package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.OrderMenu;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.List;

public interface OrderMenuService {


    public OrderMenu get(String authorization, String orderId);

    public List<OrderMenu> getList(String authorization, Timestamp orderId);

    public List<OrderMenu> post(String authorization, OrderMenu.RequestList requestList);

    public OrderMenu patch(String authorization, OrderMenu.Request request);

    public void delete(String authorization, String orderId);
}