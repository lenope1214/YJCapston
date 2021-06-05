package com.jumanji.capston.repository;

import com.jumanji.capston.data.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<Option, String> {
    List<Option> findByOptionGroup_IdContains(String optionGroupId);
}
