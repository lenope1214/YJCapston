package com.example.jmjapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private String shopId;
    private int empNo;
    private String empName;
    private String birthday;
    private String hiredate;
    private String phone;
    private char gender;
}
