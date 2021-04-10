package com.jumanji.capston.data;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
public class Penalty {

    @Id
//    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "reg_time")
    private Timestamp regTime;
    private String name;
    private String reason;

    public class Request{

    }
}
