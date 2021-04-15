package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.Order;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;

/** Order service Interface **/


public interface OrderService {

    ResponseEntity<?> get(Timestamp cartId);
    ResponseEntity<?> getList(String userId);
    ResponseEntity<?> post(String authorization, Order.Request request);
    ResponseEntity<?> patch(String authorization, Order.Request request);
    ResponseEntity<?> delete(Timestamp cartId);
}
