package com.jumanji.capston.repository;

import com.jumanji.capston.data.UserCoupon;
import com.jumanji.capston.data.UserCouponId;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserHasCouponRepository extends JpaRepository<UserCoupon, UserCouponId> {

}