package com.jumanji.capston.service;

import com.jumanji.capston.data.OptionGroup;
import com.jumanji.capston.data.OrderMenuOption;
import com.jumanji.capston.service.interfaces.BasicService;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.List;

@Service
public class OrderMenuOptionServiceImpl implements BasicService<OrderMenuOption, OrderMenuOption.Request> {


    @Override
    public OrderMenuOption get(@Nullable String authorization, String... str) {
        return null;
    }

    @Override
    public List<OrderMenuOption> getList(@Nullable String authorization, String... str) {
        return null;
    }

    @Override
    public OrderMenuOption post(@Nullable String authorization, OrderMenuOption.Request request) {
        return null;
    }

    @Override
    public OrderMenuOption patch(@Nullable String authorization, OrderMenuOption.Request request) {
        return null;
    }

    @Override
    public void delete(@Nullable String authorization, String... str) {

    }

    @Override
    public OrderMenuOption isPresent(String id) {
        return null;
    }

    @Override
    public boolean isEmpty(String id) {
        return false;
    }
}
