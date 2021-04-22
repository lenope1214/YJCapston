package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.Order;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.List;

/** Order service Interface **/


public interface OrderService {

    Order get(String authorization, Timestamp cartId);
    List<Order> getList(String authorization, String userId);
    Order post(String authorization, Order.Request request);
    Order patch(String authorization, Order.Request request);
    void delete(String authorization, Timestamp cartId);
}
