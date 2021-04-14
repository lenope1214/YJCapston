package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.OrderMenu;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    public ResponseEntity<?> get(String orderId);
    public ResponseEntity<?> getList(String cartId);
    public ResponseEntity<?> post(OrderMenu.Request request);
    public ResponseEntity<?> patch(OrderMenu.Request request);
    public ResponseEntity<?> delete(String orderId);
}
