package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.Cart;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;

/** cart service Interface **/


public interface CartService {

    ResponseEntity<?> get(Timestamp cartId);
    ResponseEntity<?> getList(String userId);
    ResponseEntity<?> post(Cart.Request request);
    ResponseEntity<?> patch(Cart.Request request);
    ResponseEntity<?> delete(Timestamp cartId);
}
