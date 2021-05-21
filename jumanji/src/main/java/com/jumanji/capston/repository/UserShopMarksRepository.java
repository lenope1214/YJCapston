package com.jumanji.capston.repository;

import com.jumanji.capston.data.UserShopMark;
import com.jumanji.capston.data.UserShopMarkId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserShopMarksRepository extends JpaRepository<UserShopMark, UserShopMarkId> {

}
