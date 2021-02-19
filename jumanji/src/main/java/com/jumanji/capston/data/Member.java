package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="member")
public class Member {
    @Id
    @Column(name = "id", length=30)
    private String id; //아이디
    @Column(length = 2)
    private String lv; // 등급
    @Column
    private int point; // 포인트
    @Column
    private int penalty; // 경고회수

}