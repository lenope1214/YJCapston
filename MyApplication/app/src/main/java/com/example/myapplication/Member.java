package com.example.myapplication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private long id;
    private String name;
    private int age;
    private String address;
    private Date createdAt;
}