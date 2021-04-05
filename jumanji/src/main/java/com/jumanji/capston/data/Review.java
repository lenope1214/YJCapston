package com.jumanji.capston.data;
import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;


@Getter
@Entity
@Table(name="reviews")
public class Review {
    @Id
    private Long id; //리뷰번호

    @ManyToOne
    @JoinColumn(nullable = false)
    private Shop shop;//매장번호 !!복합키설정 필요함!!
    private String content;//리뷰내용
    @Column(name = "reg_time")
    private Timestamp regTime;//등록일
    @Column(name = "parent_no")
    private int parentNo; //부모글번호
    private int score;//리뷰 점수
    @Column(name = "img_url")
    private String imgUrl;//이미지경로
    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;


}
