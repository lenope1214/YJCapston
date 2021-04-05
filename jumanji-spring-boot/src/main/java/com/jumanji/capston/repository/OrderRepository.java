package com.jumanji.capston.repository;

import com.jumanji.capston.data.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
