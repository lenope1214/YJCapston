package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.Bucket;
import com.jumanji.capston.data.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    public ResponseEntity<?> get(String orderId);
    public ResponseEntity<?> getList(String bucketId);
    public ResponseEntity<?> post(Order.Request request);
    public ResponseEntity<?> patch(Order.Request request);
    public ResponseEntity<?> delete(String orderId);
}
