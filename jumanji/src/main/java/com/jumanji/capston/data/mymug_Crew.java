//package com.jumanji.capston.data;
//import lombok.Data;
//
//import javax.persistence.*;
//import java.awt.*;
//import java.util.List;
//
//@Data
//@Entity
//@Table(name="crew")
//public class mymug_Crew {
//    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY) // int. autoincrement
//    private int crewid;
//    @OneToMany
//    @JoinColumn(name="crew")
//    private List<Menu> menus;
//    @Column(nullable=false,length=100)
//    private String nickname;
//    @Column(nullable=false,length=100)
//    private String name;
//
//}