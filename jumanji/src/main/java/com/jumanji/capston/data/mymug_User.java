//package com.jumanji.capston.data;
//import lombok.Data;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@Data
//@Entity
//@Table(name="user")
//public class mymug_User {
//    @Id
//    private String userid;
//    @Column(nullable=false,length=100)
//    private String name;
//    @Column(nullable = false,length=100)
//    private String password="1111";
//    @Column(nullable = false,length=100)
//    private String role="user";
//
//    private int point;
//    public mymug_User(){}
//    public mymug_User(String userid, String name) {
//        this.userid = userid;
//        this.name = name;
//    }
//}