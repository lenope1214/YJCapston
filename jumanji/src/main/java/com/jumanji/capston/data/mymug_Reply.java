//package com.jumanji.capston.data;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Getter
//@Setter
//@Entity
//@Table (name="reply")
//public class mymug_Reply {
//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY) // int. autoincrement
//    private long rno;
//    @Column
//    private String comment="";
//    @Column
//    private String rWriter;
//    @Column
//    private Date regDate=new Date();
//    @Column
//    private int up=0;
//    @Column
//    private int down=0;
//    @Column
//    private long reRno;
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name="pno")
//    private mymug_Board pno;
//}