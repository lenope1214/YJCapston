package com.jumanji.capston.service;

import com.jumanji.capston.data.Menu;
import com.jumanji.capston.data.Option;
import com.jumanji.capston.data.OptionGroup;
import com.jumanji.capston.data.Shop;
import com.jumanji.capston.repository.OptionRepository;
import com.jumanji.capston.service.interfaces.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Nullable;
import java.util.List;

@Service
public class OptionServiceImpl implements BasicService<Option, Option.Request> {
    // DI
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ShopServiceImpl shopService;
    @Autowired
    MenuServiceImpl menuService;
    @Autowired
    OptionGroupServiceImpl optionGroupService;
    @Autowired
    OptionRepository optionRepository;

    @Override
    public Option get(@Nullable String authorization, String... str) {
        return null;
    }

    @Override
    public List<Option> getList(@Nullable String authorization, String... str) {
        return null;
    }

    @Override
    public Option post(@Nullable String authorization, Option.Request request) {
        // 변수선언
        String loginId = userService.getMyId(authorization);
        String ogId =request.getOptionGroupId();
        String opId = ogId.substring(ogId.indexOf("og") + 2);
        OptionGroup optionGroup;

        // 값 확인
        System.out.println("post option - opId : " + opId);

        // 유효성 검사
        userService.isPresent(loginId);
        optionGroup = optionGroupService.isPresent(request.getOptionGroupId());

        Option option = Option.builder()
                .id(opId)
                .name(request.getName())
                .max(request.getMax())
                .price(request.getPrice())
                .optionGroup(optionGroup)
                .build();

        return optionRepository.save(option);
    }

    @Override
    public Option patch(@Nullable String authorization, Option.Request request) {
        return null;
    }

    @Override
    public void delete(@Nullable String authorization, String... str) {

    }

    @Override
    public Option isPresent(String id) {
        return null;
    }

    @Override
    public boolean isEmpty(String id) {
        return false;
    }
}
