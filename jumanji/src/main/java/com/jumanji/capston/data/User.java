package com.jumanji.capston.data;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="users")
public class User {
    @Id
    private String id; //아이디
    private String password; // 비밀번호
    private String name; // 이름
    private String email; // 이메일
    private String address; // 주소
    private String address_detail;
    private Date birthday; // 생년월일
    private String phone; // 전화번호
    private char is_wdrw; // 탈퇴여부
    private String role; // 권한   u, o, a
    private String provider; // 소셜
    private Date sign_date; // 가입날짜
    private String level; // 등급
    private int point; // 포인트
    private String provider_id;


    @Builder
    public User(String id, String password, String name, String role, String email, Date sign_date, String provider, String provider_id, String phone) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
        this.sign_date = sign_date;
        this.provider = provider;
        this.provider_id = provider_id;
        this.phone = phone;
    }
//Test 용. Column 어노테이션 없어도 테이블에 추가 되는가?
    // Column 어노테이션이 없어도 잘 됨.
//    private Timestamp regTime;
//    @Column
//    private String token; // jwt.io 문서읽기
//    @Column
//    private int penalty; // 경고회수
}
