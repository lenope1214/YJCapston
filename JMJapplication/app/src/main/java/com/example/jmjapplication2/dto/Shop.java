package com.example.jmjapplication2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shop {
    private String id;
    private String name;
    private String intro;
    private int open;
    private int close;
    private String address;
    private char is_res_pos;
}
