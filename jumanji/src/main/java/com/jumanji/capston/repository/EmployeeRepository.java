package com.jumanji.capston.repository;

import com.jumanji.capston.data.Employee;
import com.jumanji.capston.data.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query(value = "select * from employees where id like :shopId%",
            nativeQuery = true)
    List<Employee> findByShopId(String shopId);
}