package com.jumanji.capston.repository;

import com.jumanji.capston.data.OrderMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, String> {
    @Query(value = "SELECT ORDER_SEQ.nextval FROM dual", nativeQuery = true)
    Long getOrderSeqNextVal();

    int countByIdContains(String orderId);

    Set<OrderMenu> findByIdContains(String orderId);
}
