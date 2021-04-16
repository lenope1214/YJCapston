package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.UserHasCoupon;
import org.springframework.http.ResponseEntity;

public interface UserHasCouponService {
    public ResponseEntity<?> get(String userCouponId);
    public ResponseEntity<?> getList();
    public ResponseEntity<?> post(UserHasCoupon.Request request);
    public ResponseEntity<?> patch(UserHasCoupon.Request request);
    public ResponseEntity<?> delete(String userCouponId);
}
