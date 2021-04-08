package com.jumanji.capston.repository;

import com.jumanji.capston.data.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, String> {



    List<Shop> findByCategory(String category);

    List<Shop> findAllByOwner_Id(String owner_id);

    Shop findByOwner(String id);

    @Query(value = "select s from Shop s where s.owner.id=?1")
    Shop findByOwnerId(String ownerId);
}
