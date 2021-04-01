package com.example.jmjapplication2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class  Shop {
    private String id;
    private String name;
    private String intro;
    private String openTime;
    private String closeTime;
    private String address;
    private String addressDetail;
    private char isResPos;
    private String category;
    private char isOpen;


    public Shop(String id) {
    }
}
