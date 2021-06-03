package com.jumanji.capston.repository;

import com.jumanji.capston.data.Board;
import com.jumanji.capston.dto.BoardDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempRepository extends JpaRepository<Board, Long> {
    
    // db 쿼리문 작성 하는 곳

}
