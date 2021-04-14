package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.Order;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;

/** cart service Interface **/


public interface CartService {

    ResponseEntity<?> get(Timestamp cartId);
    ResponseEntity<?> getList(String userId);
    ResponseEntity<?> post(Order.Request request);
    ResponseEntity<?> patch(Order.Request request);
    ResponseEntity<?> delete(Timestamp cartId);
}
