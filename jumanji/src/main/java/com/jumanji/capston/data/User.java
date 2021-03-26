package com.jumanji.capston.data;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name="users")
public class User {
    @Id
    private String id; //아이디
    private String password; // 비밀번호
    private String name; // 이름
    private String email; // 이메일
    private String address; // 주소
    @Column(name = "address_detail")
    private String addressDetail;
    private Date birthday; // 생년월일
    private String phone; // 전화번호
    @Column(name = "is_wdrw")
    private char isWdrw; // 탈퇴여부
    private String role; // 권한   u, o, a
    @Column(name = "sign_date")
    private Date signDate; // 가입날짜
    @DateTimeFormat(pattern = "yyyyMMdd")
    @Column(name = "vip_level")
    private String level; // 등급
    private int point; // 포인트
    private String provider; // 소셜
    @Column(name = "provider_id")
    private String providerId; // 해당 소셜에서의 아이디(primary key)

    @Builder
    public User(String email, String address, String addressDetail){
        this.email = email;
        this.address = address;
        this.addressDetail = addressDetail;
    }

    @Builder
    public User(String id, String password, String name, String role, String email, Date sign_date, String provider, String provider_id, String phone) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
        this.signDate = sign_date;
        this.provider = provider;
        this.providerId = provider_id;
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    @Builder(builderMethodName = "userCreation")
//    public void
}
//Test 용. Column 어노테이션 없어도 테이블에 추가 되는가?
// Column 어노테이션이 없어도 잘 됨.
//    private Timestamp regTime;
//    @Column
//    private String token; // jwt.io 문서읽기
//    @Column
//    private int penalty; // 경고회수