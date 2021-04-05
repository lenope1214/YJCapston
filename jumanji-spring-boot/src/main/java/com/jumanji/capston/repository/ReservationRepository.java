package com.jumanji.capston.repository;

import com.jumanji.capston.data.Order;
import com.jumanji.capston.data.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Order> {


}
