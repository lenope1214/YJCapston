package com.jumanji.capston.repository;

import com.jumanji.capston.data.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Timestamp> {

    List<Order> findALLByUser_Id(String user_Id);

    // 이성복 돼지???

    List<Order> findAllByShop_Id(String shopId);


    @Query(value = "select o.*,\n" +
            "       DECODE((select o.id\n" +
            "               from ORDERS o\n" +
            "                        left join REVIEWS R on o.ID = R.ORDER_ID\n" +
            "               where r.USER_ID = :userId), o.id, 'Y', 'N') reviewed\n" +
            "from orders o", nativeQuery = true)
    List<Order> myOrderListContainsReviewed(String userId);



}