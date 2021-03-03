package com.jumanji.capston.repository;



import org.springframework.stereotype.Repository;
import com.jumanji.capston.data.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {
    public List<Shop> findAll();

    public Shop findById(String id);




}
