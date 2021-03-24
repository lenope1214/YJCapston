package com.jumanji.capston.repository;

import com.jumanji.capston.data.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, String> {


    Shop findByOwnerId_Id(String id);
}
