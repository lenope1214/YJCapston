package com.jumanji.capston.repository;

import com.jumanji.capston.data.Payment;
import com.jumanji.capston.data.Refund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefundRepository extends JpaRepository<Refund, Payment> {


}
