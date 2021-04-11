package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.Coupon;
import org.springframework.http.ResponseEntity;

public interface CouponService {
    public ResponseEntity<?> get(String couponId);
    public ResponseEntity<?> getList();
    public ResponseEntity<?> post(Coupon.Request request);
    public ResponseEntity<?> patch(Coupon.Request request);
    public ResponseEntity<?> delete(String couponId);
}
