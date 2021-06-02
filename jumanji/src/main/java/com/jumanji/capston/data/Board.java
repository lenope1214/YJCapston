package com.jumanji.capston.data;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity // DB 테이블이다.
@Table(name = "Boards") // 테이블 속성 정의 하는 어노테이션
@Data @NoArgsConstructor
public class Board {
    // 기본키가 하나 이상 있어야해.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 글 번호

    private String title; // 제목 

    private String contents; // 내용

    private Date regDate; // 등록일

    public Board(String title, String contents){
        this.title = title;
        this.contents = contents;
        this.regDate = new Date();
    }
}
