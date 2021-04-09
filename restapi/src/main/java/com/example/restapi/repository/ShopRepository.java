package com.example.restapi.repository;

import com.example.restapi.model.Member;
import com.example.restapi.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, String> {
}
