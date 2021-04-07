package com.jumanji.capston.repository;

import com.jumanji.capston.data.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT ORDER_SEQ.nextval FROM dual", nativeQuery = true)
    Long getOrderSeqNextVal();
}
