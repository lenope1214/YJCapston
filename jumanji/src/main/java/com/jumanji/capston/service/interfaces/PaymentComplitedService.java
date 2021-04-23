package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.Payment;
import org.springframework.http.ResponseEntity;

public interface PaymentComplitedService {
    public ResponseEntity<?> get(String paymentId);
    public ResponseEntity<?> getList(String orderId);
    public ResponseEntity<?> post(Payment.Request request);
    public ResponseEntity<?> patch(Payment.Request request);
//    public ResponseEntity<?> delete(String paymentId);
}
