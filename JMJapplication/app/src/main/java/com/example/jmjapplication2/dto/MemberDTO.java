package com.example.jmjapplication2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private String id;
    private String pw;
    private String name;
    private String email;
    private String address;
    private Date birthday;
    private String phone;
    private char is_wdrw;
    private String role;
    private String social;
    private Date sign_date;
    private String lv;
    private int point;
    private int code;
}
