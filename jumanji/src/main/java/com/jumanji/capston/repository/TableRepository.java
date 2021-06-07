package com.jumanji.capston.repository;

import com.jumanji.capston.data.Tab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableRepository extends JpaRepository<Tab, String > {

    public List<Tab> findByIdContains(String shopId);
}