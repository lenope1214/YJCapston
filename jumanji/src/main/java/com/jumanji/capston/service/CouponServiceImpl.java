package com.jumanji.capston.service;

import com.jumanji.capston.data.Coupon;
import com.jumanji.capston.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl {
    @Autowired
    CouponRepository couponRepository;

    public Coupon findById(Long id){
        return couponRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("쿠폰 id를 확인해주세요!!!"));
    }


    public Coupon insert(Coupon _coupon){
        return couponRepository.save(_coupon);
    }

    public String delete(Coupon _coupon){
        Coupon coupon  = couponRepository.findById(_coupon.getId()).orElseThrow(()-> new IllegalArgumentException("쿠폰 id를 확인해주세요!!!"));
        couponRepository.delete(coupon);
        return "ok";
    }

    public List<Coupon> findAll() {
        return couponRepository.findAll();
    }
}
