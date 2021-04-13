package com.jumanji.capston.repository;

import com.jumanji.capston.data.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, String> {
    @Query(value = "SELECT ORDER_SEQ.nextval FROM dual", nativeQuery = true)
    Long getOrderSeqNextVal();

    int countByIdContains(String cartId);

    Set<Order> findByIdContains(String cartId);
}
