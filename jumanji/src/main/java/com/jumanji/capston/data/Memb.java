package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name="memb")
public class Memb implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name="member",nullable = false)
    private Member mem_id; //아이디
    @Column(length = 2,nullable = false)
    private String lv; // 등급
    @Column(nullable = false)
    private int point; // 포인트
    @Column
    private int penalty; // 경고회수

}