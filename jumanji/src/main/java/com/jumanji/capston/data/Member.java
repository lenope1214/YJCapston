package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="member")
public class Member {
    @Id
    @Column(name = "mem_id", length=30,nullable = false)
    private String id; //아이디
    @Column(length = 2,nullable = false)
    private String lv; // 등급
    @Column(nullable = false)
    private int point; // 포인트
    @Column
    private int penalty; // 경고회수

}