package com.jumanji.capston.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    // data transfer object
    // 데이터 전송 객체
    private Long id;
    private String title;
    private String contents;
}
