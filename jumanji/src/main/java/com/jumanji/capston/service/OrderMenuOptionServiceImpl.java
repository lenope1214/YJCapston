//package com.jumanji.capston.service;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.jumanji.capston.data.Option;
//import com.jumanji.capston.data.OptionGroup;
//import com.jumanji.capston.data.OrderMenu;
//import com.jumanji.capston.data.OrderMenuOption;
//import com.jumanji.capston.service.interfaces.BasicService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import java.util.List;
//
//@Service
//public class OrderMenuOptionServiceImpl {
//    @Autowired
//    UserServiceImpl userService;
//
//    public OrderMenuOption get(@Nullable String authorization, String... str) {
//        return null;
//    }
//
//    public List<OrderMenuOption> getList(@Nullable String authorization, String... str) {
//        return null;
//    }
//
//    public OrderMenuOption post(@Nullable String authorization, List<OrderMenuOption.Request> request) {
//        // 변수
//        String loginId = userService.getMyId(authorization);
//
//        // 값 체크
//
//        // 유효성 체크
//         // orderMenu, option 얘네 유효성 체크는 패스하자.. 너무 무겁다
//        for(OrderMenuOption.Request req : request){
//
//        }
//        // 서비스
//        return null;
//    }
//
//    public OrderMenuOption patch(@Nullable String authorization, OrderMenuOption.Request request) {
//        return null;
//    }
//
//    public void delete(@Nullable String authorization, String... str) {
//
//    }
//
//    public OrderMenuOption isPresent(String id) {
//        return null;
//    }
//
//    public boolean isEmpty(String id) {
//        return false;
//    }
//}
