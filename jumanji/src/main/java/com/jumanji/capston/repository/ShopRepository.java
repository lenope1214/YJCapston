package com.jumanji.capston.repository;

import com.jumanji.capston.data.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, String> {


    Shop findByOwnerId_Id(String id);

    List<Shop> findByCategory(String category);
}
