package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name="member")
public class Member {
    @Id
    @Column(name="member_id", length=30)
    private String mem_id; //아이디
    @Column(length = 30, nullable = false)
    private String pw; // 비밀번호
    @Column(length = 15, nullable = false)
    private String name; // 이름
    @Column(length = 70)
    private String email; // 이메일
    @Column(length = 120)
    private String address; // 주소
    @Column
    private Date birthday; // 생년월일
    @Column(length = 11, nullable = false)
    private String phone; // 전화번호
    @Column
    private char is_wdrw='N'; // 탈퇴여부
    @Column
    private String auth; // 권한
    @Column(length = 2)
    private String social; // 소셜
    @Column(insertable = false, updatable = false) // 업데이트, 인설트 불가
    private Date sign_date = new Date(); // 가입날짜
    @Column(length=2)
    private String lev;//등급
    @Column(length=6)
    private int point;//포인트
}