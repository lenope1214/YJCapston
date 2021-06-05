package com.jumanji.capston.service;

import com.jumanji.capston.data.UserCoupon;
import com.jumanji.capston.data.UserCouponId;
import com.jumanji.capston.repository.UserHasCouponRepository;
import com.jumanji.capston.service.interfaces.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserHasCouponServiceImpl implements BasicService<UserCoupon, UserCoupon.Request, UserCouponId> {
    @Autowired
    UserHasCouponRepository userHasCouponRepository;

    @Override
    public UserCoupon get(@Nullable String authorization, String... str) {
        return null;
    }

    @Override
    public List<UserCoupon> getList(@Nullable String authorization, String... str) {
        return null;
    }

    @Override
    public UserCoupon post(@Nullable String authorization, UserCoupon.Request request) {
        return null;
    }

    @Override
    public UserCoupon patch(@Nullable String authorization, UserCoupon.Request request) {
        return null;
    }

    @Override
    public void delete(@Nullable String authorization, String... str) {

    }

    @Override
    public UserCoupon isPresent(UserCouponId id) {
        return null;
    }

    @Override
    public boolean isEmpty(UserCouponId id) {
        return false;
    }
}
