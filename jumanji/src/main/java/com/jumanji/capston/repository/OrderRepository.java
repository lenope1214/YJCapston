package com.jumanji.capston.repository;

import com.jumanji.capston.data.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Timestamp> {

    List<Order> findALLByUser_Id(String user_Id);

    List<Order> findAllByShop_Id(String shopId);
}