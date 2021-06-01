package com.example.jmjapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Options {
    private String optionId;
    private String name;
    private int price;
    private int optionMax; // 최대 개수
    private String optionGroupId;
}
