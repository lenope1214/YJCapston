package com.jumanji.capston.service;

import com.jumanji.capston.data.*;
import com.jumanji.capston.repository.OptionRepository;
import com.jumanji.capston.service.exception.optionException.OptionHasExistException;
import com.jumanji.capston.service.exception.optionException.OptionNotFoundException;
import com.jumanji.capston.service.exception.userException.UserHasExistException;
import com.jumanji.capston.service.exception.userException.UserNotFoundException;
import com.jumanji.capston.service.interfaces.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

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
        String oGroupId = str[0];
        List<Option> optionList = optionRepository.findByOptionGroup_IdContains(oGroupId);
        return optionList;
    }

    @Override
    public Option post(@Nullable String authorization, Option.Request request) {
        // 변수선언
        String loginId = userService.getMyId(authorization);
        String ogId =request.getOptionGroupId();
        String opId = ogId.substring(ogId.indexOf("og") + 2) + String.format("%02d", request.getNo());
        OptionGroup optionGroup;

        // 값 확인
        System.out.println("post option - opId : " + opId);

        // 유효성 검사
        userService.isPresent(loginId);
        optionGroup = optionGroupService.isPresent(request.getOptionGroupId());
        isEmpty(opId);

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
        Optional<Option> option = optionRepository.findById(id);
        if(option.isPresent())return option.get();
        throw new OptionNotFoundException();
    }

    @Override
    public boolean isEmpty(String id) {
        if(optionRepository.findById(id).isEmpty())return true;
        throw new OptionHasExistException(id);
    }

}
