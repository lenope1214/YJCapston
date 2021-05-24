package com.jumanji.capston.repository;

import com.jumanji.capston.data.Shop;
import com.jumanji.capston.data.UserShopMark;
import com.jumanji.capston.data.UserShopMarkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserShopMarksRepository extends JpaRepository<UserShopMark, UserShopMarkId> {

    @Query(value = "select usm.id.shop from UserShopMark usm where usm.id.user.id = :loginId")
    List<Shop> findMyMarks(String loginId);

    @Query(value = "delete from UserShopMark where id.user.id = :id and id.shop.id = :shopId")
    void deleteByShopID(String id, String shopId);

}
