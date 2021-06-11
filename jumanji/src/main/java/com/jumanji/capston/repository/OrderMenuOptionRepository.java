package com.jumanji.capston.repository;

import com.jumanji.capston.data.Option;
import com.jumanji.capston.data.OrderMenuOption;
import com.jumanji.capston.data.OrderMenuOptionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMenuOptionRepository extends JpaRepository<OrderMenuOption, OrderMenuOptionId> {

}
