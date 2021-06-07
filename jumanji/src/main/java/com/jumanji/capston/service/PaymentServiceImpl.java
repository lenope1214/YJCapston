package com.jumanji.capston.service;

import com.jumanji.capston.data.Order;
import com.jumanji.capston.data.Payment;
import com.jumanji.capston.data.User;
import com.jumanji.capston.repository.OrderRepository;
import com.jumanji.capston.service.exception.orderException.PayPointOverException;
import com.jumanji.capston.service.interfaces.BasicService;
import com.jumanji.capston.service.interfaces.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ShopServiceImpl shopService;
    @Autowired
    OrderRepository orderRepository;

    /**
     * 
     * @param authorization jwt
     * @param shopId 식당번호
     * @param scope 일, 주, 월
     * @param date 요청 기준 날짜
     * @return
     */
    public List<Payment> getShopStatistics(String authorization, String shopId, String scope, String date){
        
        String loginId = userService.getMyId(authorization);


        // 유효성 체크
        shopService.isOwnShop(loginId, shopId);
        
        return null;
    }


    @Override
    public Payment get(String authorization, Timestamp orderId) {
        return null;
    }

    @Override
    public List<Payment> getList(String orderId) {
        return null;
    }

    @Override
    @Transactional
    public Payment post(String authorization, Payment.Request request) {
        String loginId = userService.getMyId(authorization);
        User user = userService.isPresent(loginId); // 그 사용자가 맞는지 확인
        int remainPoint = user.getPoint() - request.getUsePoint();

        // order가 내거인지
        System.out.println("내 주문이 맞는지?");
        Order order = orderService.isOwnOrder(request.getOrderId(), loginId); // 해당 사용자의 주문이 맞는지
        System.out.println("맞는지 검사 후");
        order.pay(request);
        System.out.println(order.toString());
        System.out.println("남은 포인트 : " + remainPoint);
        if(remainPoint >= 0)
            user.setPoint(remainPoint);
        else
            throw new PayPointOverException();
        Payment payment = new Payment(order);
        System.out.println("결제시간 : " + payment.getPayTime());
        System.out.println();
        orderRepository.saveAndFlush(order);
        userService.save(user);
        return payment;
    }

    // 1620366111800
    // 1620366815170
    @Override
    public Payment patch(String authorization, Payment.Request request) {
        return null;
    }


    public Object isPresent(String id) {
        return false;
    }


    public boolean isEmpty(String id) {
        return false;
    }
}
