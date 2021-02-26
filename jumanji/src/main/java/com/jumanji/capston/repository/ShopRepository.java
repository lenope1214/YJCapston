package com.jumanji.capston.repository;



import org.springframework.stereotype.Repository;
import com.jumanji.capston.data.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, String> {
    public List<Shop> findAll();
    public Shop findById(int id);
}
