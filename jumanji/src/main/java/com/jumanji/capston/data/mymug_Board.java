package com.codesample.mymug.data;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="board")
public class Board {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) // int. autoincrement
    private long pno;
    @Column(nullable=false,length=100)
    private String title; // 제목
    @Column
    private long views; // 조회수
    @Column(length=500)
    private String content; // 내용
    @Column
    private Date regDate; // 등록일
    @Column(length=15)
    private String password;
    @OneToMany
    @JoinColumn(name="pno")
    private List<Reply> replys;
//    @Column
//    private String writer; //작성자 -> 익명처리 해야함
}
