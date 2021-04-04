package com.example.restapi.model;//package com.jumanji.capston.data;
//import lombok.Data;
//
//import javax.persistence.*;
//
//@Data
//@Entity
//@Table(name="menu")
//public class mymug_Menu {
//    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY) // int. autoincrement
//    private int menuid;
//    @ManyToOne(targetEntity=mymug_Crew.class)
//    @JoinColumn(name="crew")
//    private mymug_Crew crew;
//    @Column(nullable=false,length=100)
//    private String name;
//    @Column
//    private int price;
//}