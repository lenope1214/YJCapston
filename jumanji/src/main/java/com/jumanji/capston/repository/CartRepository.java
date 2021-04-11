package com.jumanji.capston.repository;

import com.jumanji.capston.data.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, String> {

    List<Cart> findALLByUser_Id(String user_Id);
}