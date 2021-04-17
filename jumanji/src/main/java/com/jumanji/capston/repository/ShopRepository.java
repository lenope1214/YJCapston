package com.jumanji.capston.repository;

import com.jumanji.capston.data.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, String> {

//    @Query(value = "select s from Shop s order by (select sum(?1) )")
//    List<Shop> findBySort(String sort);

    //from shops s
    //where s.category = nvl('일식', s.category)
    //order by (select sum(score) / count(score) from REVIEWS ) desc;
    @Query(value = "select * from shops s where s.category = coalesce(:category, s.category) order by (select sum(r.score) / count(r.score) from REVIEWS r)", nativeQuery = true)
//    @Query(value = "select * from shops s where s.category = coalesce(:category, s.category) order by (select sum(r.score) / count(r.score) from REVIEWS r)", nativeQuery = true)
//    @Query(value = "select s from  Shop s  where s.category = coalesce(?1, s.category) order by (select sum(score)/count(score) from Review ) desc ")
    List<Shop> ShopOrderByScore(String category);

    @Query(value = "select :category from dual", nativeQuery = true)
    List<Shop> test(String category);

    List<Shop> findByCategory(String category);

    List<Shop> findAllByOwner_Id(String owner_id);

    @Query(value = "select s from Shop s where s.owner.id=?1")
    List<Shop> findByOwnerId(String ownerId);
}
