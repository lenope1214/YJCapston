package com.jumanji.capston.repository;

import com.jumanji.capston.data.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;


public interface MenuRepository extends JpaRepository<Menu, String> {
    @Query(value = "SELECT MENU_SEQ.nextval FROM dual", nativeQuery = true)
    public BigDecimal getMenuSeqNextVal();
    List<Menu> findByIdContains(String id);
}