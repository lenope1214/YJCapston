package com.jumanji.capston.repository;

import com.jumanji.capston.data.Menu;
import com.jumanji.capston.data.Tab;
import org.springframework.data.jpa.repository.JpaRepository;



public interface MenuRepository extends JpaRepository<Menu, String> {

}