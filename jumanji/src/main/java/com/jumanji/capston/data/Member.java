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
    private String member_id; //아이디
    @Column(name="pw",length = 100, nullable = false) // 암호화를 하는데 여유자리까지 충분하게 100자리.
    private String pw; // 비밀번호
    @Column(name="name",length = 15, nullable = false)
    private String name; // 이름
    @Column(name="email",length = 70)
    private String email; // 이메일
    @Column(name="address",length = 90)
    private String address; // 주소
    @Column(name="address_detail",length = 90)
    private String address_detail; // 상세주소
    @Column(name="birthday")
    private Date birthday; // 생년월일
    @Column(name="phone",length = 11, nullable = false)
    private String phone; // 전화번호
    @Column(name="is_wdrw")
    private char is_wdrw='N'; // 탈퇴여부
    @Column(name="auth")
    private char auth; // 권한
    @Column(name="social",length = 2)
    private char social; // 소셜
    @Column(name="sign_date",insertable = false, updatable = false) // 업데이트, 인설트 불가
    private Date sign_date = new Date(); // 가입날짜
    @Column(name="lv", length = 2,nullable = false)
    private int lv; // 등급
    @Column(name="point",length=6,nullable = false)
    private int point; // 포인트
//    @Column
//    private int penalty; // 경고회수
}
