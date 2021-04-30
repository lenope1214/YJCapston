package com.jumanji.capston.service;

import com.jumanji.capston.data.UserHasCoupon;
import com.jumanji.capston.data.UserHasCouponId;
import com.jumanji.capston.repository.UserHasCouponRepository;
import com.jumanji.capston.service.interfaces.BasicService;
import com.jumanji.capston.service.interfaces.UserHasCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserHasCouponServiceImpl implements UserHasCouponService, BasicService {
    @Autowired
    UserHasCouponRepository userHasCouponRepository;

    public UserHasCoupon findById(UserHasCouponId id) {
        return userHasCouponRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id를 확인해주세요!!!"));
    }


    public UserHasCoupon insert(UserHasCoupon _userHasCoupon) {
        return userHasCouponRepository.save(_userHasCoupon);
    }

    public String delete(UserHasCouponId _userHasCouponId) {
        UserHasCoupon userHasCoupon = userHasCouponRepository.findById(_userHasCouponId).orElseThrow(() -> new IllegalArgumentException("id를 확인해주세요!!!"));
        userHasCouponRepository.delete(userHasCoupon);
        return "ok";
    }

    public List<UserHasCoupon> findAll() {
        return userHasCouponRepository.findAll();
    }

    @Override
    public ResponseEntity<?> get(String userCouponId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getList() {
        return null;
    }

    @Override
    public ResponseEntity<?> post(UserHasCoupon.Request request) {
        return null;
    }

    @Override
    public ResponseEntity<?> patch(UserHasCoupon.Request request) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(String userCouponId) {
        return null;
    }

    @Override
    public boolean isPresent(String id) {
        return false;
    }

    @Override
    public boolean isEmpty(String id) {
        return false;
    }
}
