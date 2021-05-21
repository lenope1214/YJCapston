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
public class UserShopMarkService  {
    private final UserServiceImpl userService;
    private final ShopServiceImpl shopService;
    private final UserShopMarksRepository usmRepository;


    public UserShopMark get(@Nullable String authorization, String... str) {
        return null;
    }


    public List<Shop> getList(@Nullable String authorization, String... str) {
        // 변수
        String loginId;
        // 값 체크
        // 유효성 체크
        loginId = userService.getMyId(authorization);
        // 서비스
        List<Shop> shopList = usmRepository.findMyMarks(loginId);
        // 값 체크
        System.out.println("shopList >>>>>>>>>>>>>>>>");
        for (Shop shop : shopList){
            System.out.println("shop id : " + shop.getId());
            System.out.println("shop name : " + shop.getName());
        }
        System.out.println("shopList <<<<<<<<<<<<<<<<");
        return shopList;
    }


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


    public UserShopMark patch(@Nullable String authorization, UserShopMark.Request request) {
        return null;
    }


    public void delete(@Nullable String authorization, String... str) {

    }


    public UserShopMark isPresent(String id) {
        return null;
    }


    public boolean isEmpty(String id) {
        return false;
    }
}
