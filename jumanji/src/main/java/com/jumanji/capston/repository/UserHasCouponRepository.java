package com.jumanji.capston.repository;

import com.jumanji.capston.data.UserHasCoupon;
import com.jumanji.capston.data.UserHasCouponId;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserHasCouponRepository extends JpaRepository<UserHasCoupon, UserHasCouponId> {

}