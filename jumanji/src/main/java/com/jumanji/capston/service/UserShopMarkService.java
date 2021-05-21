package com.jumanji.capston.service;

import com.jumanji.capston.data.Shop;
import com.jumanji.capston.data.User;
import com.jumanji.capston.data.UserShopMark;
import com.jumanji.capston.repository.UserShopMarksRepository;
import com.jumanji.capston.service.interfaces.BasicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserShopMarkService implements BasicService<UserShopMark, UserShopMark.Request> {
    private final UserServiceImpl userService;
    private final ShopServiceImpl shopService;
    private final UserShopMarksRepository usmRepository;

    @Override
    public UserShopMark get(@Nullable String authorization, String... str) {
        return null;
    }

    @Override
    public List<UserShopMark> getList(@Nullable String authorization, String... str) {
        // 변수
        User user;
        // 값 체크
        // 유효성 체크
        user = userService.isLogin(authorization);

        // 서비스

        // 값 체크
        return null;
    }

    @Override
    public UserShopMark post(@Nullable String authorization, UserShopMark.Request request) {
        // 변수
        UserShopMark usm;
        User user;
        Shop shop;
        // 값 체크4

        // 유효성 체크
        user = userService.isLogin(authorization);
        shop = shopService.isPresent(request.getShopId());

        // 서비스
        usm = new UserShopMark(user, shop);

        // 값 확인
        return usmRepository.save(usm);
    }

    @Override
    public UserShopMark patch(@Nullable String authorization, UserShopMark.Request request) {
        return null;
    }

    @Override
    public void delete(@Nullable String authorization, String... str) {

    }

    @Override
    public UserShopMark isPresent(String id) {
        return null;
    }

    @Override
    public boolean isEmpty(String id) {
        return false;
    }
}
