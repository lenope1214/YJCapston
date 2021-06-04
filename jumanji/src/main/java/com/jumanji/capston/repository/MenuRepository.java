package com.jumanji.capston.repository;

import com.jumanji.capston.data.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MenuRepository extends JpaRepository<Menu, String> {
//    @Query(value = "SELECT MENU_SEQ.nextval FROM dual", nativeQuery = true)
//    public BigDecimal getMenuSeqNextVal();
List<Menu> findByIdContains(String id);

    Menu findByIdOrName(String id, String name);


    List<Menu> findByShopId(String id);

    int countMenusByIdContains(String id);


//    @Query(value = "SELECT max((select * from Shop where id = shopId)) from Menu")
//    long maxId(@Param("shopId")String shopId);

//    @Query(value = "select max() from Menu")
//    long maxId();
}