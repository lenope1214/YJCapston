package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name="account")
public class Account {
    @Id
    @Column(length = 30)
    private String id; //아이디
    @Column(length = 30)
    private String pw; // 비밀번호
    @Column(length = 15)
    private String name; // 이름
    @Column(length = 70)
    private String email; // 이메일
    @Column(length = 120)
    private String address; // 주소
    @Column
    private Date birthday; // 생년월일
    @Column(length = 11)
    private String phone; // 전화번호
    @Column
    private char is_wdrw; // 탈퇴여부
    @Column
    private String auth; // 권한
    @Column(length = 2)
    private String social; // 소셜
    @Column(insertable = false, updatable = false) // 업데이트, 인설트 불가
    @Temporal(TemporalType.TIMESTAMP) // timestamp 테스트 해봐야함.
    private Date sign_date; // 가입날짜
}