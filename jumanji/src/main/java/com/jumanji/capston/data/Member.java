package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name="member")
public class Member {
    @Id
    @Column(name = "member_id", length = 30)
    private String id; //아이디
    @Column(length = 100, nullable = false) // 암호화를 하는데 여유자리까지 충분하게 100자리.
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
    @Column(length = 2,nullable = false)
    private String level; // 등급
    @Column(nullable = false)
    private int point; // 포인트
    @Column
    private int penalty; // 경고회수
}
