package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="mem")
public class Mem {
    @Id
    @ManyToOne
    @JoinColumn(name="account",nullable = false)
    private Member id; //아이디
    @Column(length = 2,nullable = false)
    private String lv; // 등급
    @Column(nullable = false)
    private int point; // 포인트
    @Column
    private int penalty; // 경고회수

}