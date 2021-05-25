package com.jumanji.capston.service;

import com.jumanji.capston.data.*;
import com.jumanji.capston.repository.OrderRepository;
import com.jumanji.capston.repository.ReviewRepository;
import com.jumanji.capston.service.exception.orderException.OrderHasExistException;
import com.jumanji.capston.service.exception.orderException.OrderNotFoundException;
import com.jumanji.capston.service.interfaces.OrderService;
import jdk.jfr.TransitionTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ShopServiceImpl shopService;

//    public ResponseEntity<?> getCartId() {
//        @Getter
//        @Setter
//        class Response {
//            private Timestamp cartId;
//        }
//        Response response = new Response();
//        response.setCartId(new Timestamp(System.currentTimeMillis()));
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    public Order get(String authorization, Timestamp orderId){
        String userId = userService.getMyId(authorization);
        isOwnOrder(orderId, userId);
        return isPresent(orderId);
    }

    public List<Order> getList(String authorization) {
        String loginId = userService.getMyId(authorization);
        List<Order> orderList = new ArrayList<>();
        for (Order.MyInfo order : orderRepository.myOrderListContainsReviewed(loginId)) {
            System.out.println("order.Tostring : " + order.toString());
            /**
             * private String status = "rd"; // rd : 준비중, st : 착석, pd : 결제완료, rf : 환불
             *     @Column(name = "order_request")
             *     private String orderRequest; // 요청사항
             *     @Column(length = 2)
             *     private int people;
             *     @Column(name = "use_point", length = 5)
             *     private int usePoint; // 사용된 포인트
             *     @Column(length = 8)
             *     private int amount; // 가격 총합
             *     @Column(name = "arrive_time")
             *     private Timestamp arriveTime; // 가게 도착시간
             *     @Column(name = "pay_time")
             *     private Timestamp payTime; // 결제 일자 yyyyMMdd
             *     @Column(length = 9)
             *     private String pg;
             *     @Column(name = "pay_method")
             *     private String payMethod; // 결제방식
             *
             *     private char accept = 'N';
             *
             *     @Transient // 영속성 등록 제외?   제외하면 결과 제대로 안나옴ㅋㅋㅋ 왜???????????
             *     @Setter
             *     private char reviewed;
             */
            Order o = new Order(order);
            System.out.println("o.toString : " + o.toString());
//            o.setReviewed(order.getReviewed());
            orderList.add(o);
        }

        return orderList;
    }




    @Override
    public List<Order> getList(String authorization, String userId) {
        String loginId = userService.getMyId(authorization);
        User user = userService.isPresent(loginId);
        userService.isAuth(user.getRole(), "ADMIN");

        List<Order> orderList = new ArrayList<>();
        for (Order order : orderRepository.findALLByUser_Id(userId)) {
            orderList.add(order);
        }
        return orderList;
    }

    public Order post(String loginId, User user, Shop shop, Order.Request request) {
        Order order;
        order = Order.builder()
                .id(new Timestamp(System.currentTimeMillis()))
//                .orderRequest(request.getOrderRequest()) // 얘와 밑의 얘네 둘은 결제 완료 후에 들어갈 예정
//                .people(request.getPeople())
                .shop(shop)
                .user(user)
                .build();
                orderRepository.save(order);
        return order;
    }

    @Override
    @Transactional
    public Order patch(String authorization, Order.Request request) {
        Order order;
        User user;
        Shop shop;
        // 주문번호가 같이 들어오면, 있는 주문번호를 수정하는것이기 때문에 그대로 검색해서 수정. 없으면 새로운 주문이므로 생성

        String loginId = userService.getMyId(authorization);

        // 유효성 검사.
        user = userService.isPresent(loginId);
        shop = shopService.isPresent(request.getShopId());
        order = request.getOrderId() != null ? isOwnOrder(request.getOrderId(), loginId) : post(loginId, user, shop, request);

        order.patch(request);

        order = orderRepository.saveAndFlush(order);
        return order;
    }

//    @Override
//    public void delete(String authorization, Timestamp orderId) {
//        isPresent(orderId);
//        Order order = orderRepository.findById(orderId).get();
//        orderRepository.delete(order);
//    }

    public Order isPresent(Timestamp orderId) {
        Optional<Order> order =orderRepository.findById(orderId);
        if (order.isPresent()) return order.get();
        throw new OrderNotFoundException();
    }

    public boolean isEmpty(Timestamp orderId) {
        if (orderRepository.findById(orderId).isEmpty()) return true;
        throw new OrderHasExistException();
    }

    public Order isOwnOrder(Timestamp orderId, String userId){
        Order order = isPresent(orderId);
        String _uId =order.getUser().getId();
        if(_uId.equals(userId))return order;
        else throw new OrderNotFoundException(""+orderId.getTime());
    }

    public List<Order> getListByShopId(String authorization, String shopId) {
        String loginId;

        // 유효성 검사
        loginId = userService.getMyId(authorization);
        shopService.isOwnShop(loginId, shopId);

        List<Order> orderList;
        orderList = orderRepository.findAllByShop_Id(shopId);
        System.out.println("해당 매장의 주문목록");
        for(Order order: orderList){
            System.out.println("getOrderRequest : " + order.getOrderRequest() + "\n");
            System.out.println("getUser().getName()" + order.getUser().getName());
        }
        return orderList;
    }

    public Order orderAccept(String authorization, Order.Request request) {
        // 변수
        String loginId;
        Order order;

        // 값 체크

        // 유효성 체크
        loginId = userService.getMyId(authorization);
        shopService.isOwnShop(loginId, request.getShopId());
        order = isPresent(request.getOrderId());

        // 서비스
        order.accept();


        // 값 체크
        return orderRepository.save(order);
    }

    public void statusUpdate(Order order) {
        orderRepository.save(order);
    }

    public Object getList() {
        return orderRepository.findAll();
    }


}
