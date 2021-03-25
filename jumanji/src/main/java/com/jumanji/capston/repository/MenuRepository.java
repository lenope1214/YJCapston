package com.jumanji.capston.repository;

import com.jumanji.capston.data.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MenuRepository extends JpaRepository<Menu, String> {

    List<Menu> findByIdContains(String id);
}