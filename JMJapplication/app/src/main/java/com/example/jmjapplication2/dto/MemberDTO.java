package com.example.jmjapplication2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private String id; //아이디
    private String password; // 비밀번호
    private String name; // 이름
    private String email; // 이메일
    private String address; // 주소
    private String addressDetail;
    private Date birthday; // 생년월일
    private String phone; // 전화번호
    private char isWdrw; // 탈퇴여부
    private String role; // 권한   u, o, a
    //private long signDate; // 가입날짜
    private String level; // 등급
    private int point; // 포인트
    private String provider; // 소셜
    private String providerId; // 해당 소셜에서의 아이디(primary key)


}
