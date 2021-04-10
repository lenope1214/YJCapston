package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.Bucket;
import com.jumanji.capston.data.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    public ResponseEntity<?> get(String paymentId);
    public ResponseEntity<?> getList(String bucketId);
    public ResponseEntity<?> post(Payment.Request request);
    public ResponseEntity<?> patch(Payment.Request request);
//    public ResponseEntity<?> delete(String paymentId);
}
