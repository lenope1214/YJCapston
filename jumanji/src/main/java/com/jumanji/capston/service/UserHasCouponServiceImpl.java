package com.jumanji.capston.service;

import com.jumanji.capston.repository.UserHasCouponRepository;
import com.jumanji.capston.service.interfaces.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.List;

@Service
public class UserHasCouponServiceImpl implements BasicService {
    @Autowired
    UserHasCouponRepository userHasCouponRepository;

    @Override
    public Object get(@Nullable String authorization, String... str) {
        return null;
    }

    @Override
    public List getList(@Nullable String authorization, String... str) {
        return null;
    }

    @Override
    public Object post(@Nullable String authorization, Object request) {
        return null;
    }

    @Override
    public Object patch(@Nullable String authorization, Object request) {
        return null;
    }

    @Override
    public void delete(@Nullable String authorization, String... str) {

    }

    @Override
    public Object isPresent(String id) {
        return null;
    }

    @Override
    public boolean isEmpty(String id) {
        return false;
    }
}
