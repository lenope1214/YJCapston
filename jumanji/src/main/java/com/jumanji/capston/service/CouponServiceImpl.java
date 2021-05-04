package com.jumanji.capston.service;

import com.jumanji.capston.data.Coupon;
import com.jumanji.capston.repository.CouponRepository;
import com.jumanji.capston.service.interfaces.BasicService;
import com.jumanji.capston.service.interfaces.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CouponServiceImpl implements CouponService, BasicService {
    @Autowired
    CouponRepository couponRepository;

    @Override
    public ResponseEntity<?> get(String couponId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getList() {
        return null;
    }

    @Override
    public ResponseEntity<?> post(Coupon.Request request) {
        return null;
    }

    @Override
    public ResponseEntity<?> patch(Coupon.Request request) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(String couponId) {
        return null;
    }

    @Override
    public Object isPresent(String id) {
        return false;
    }

    @Override
    public boolean isEmpty(String id) {
        return false;
    }
}
