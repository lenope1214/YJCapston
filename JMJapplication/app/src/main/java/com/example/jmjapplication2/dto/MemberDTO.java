package com.example.jmjapplication2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private long id;
    private String userid;
    private String userpw;
    private String name;
    private String phone;
    private String role;
    private int code;
}
