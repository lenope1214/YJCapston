package com.example.restapi.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userid;

    private String userpw;

    private String name;

    private String phone;

    private String role;

    private int code;

    public Member(String userid, String userpw, String name, String phone, String role){
        this.userid = userid;
        this.userpw = userpw;
        this.name = name;
        this.phone = phone;
        this.role = role;
    }


    public Member(String userid, String userpw) {
        this.userid = userid;
        this.userpw = userpw;
    }
}
