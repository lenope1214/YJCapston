package com.jumanji.capston.repository;

import com.jumanji.capston.data.Option;
import com.jumanji.capston.data.OptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionGroupRepository extends JpaRepository<OptionGroup, String> {
    List<OptionGroup> findByMenu_Id(String menuId);
}
