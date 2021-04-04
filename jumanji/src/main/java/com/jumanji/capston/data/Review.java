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
    private Shop shop_id;//매장번호 !!복합키설정 필요함!!
    private String content;//리뷰내용
    private Timestamp reg_time;//등록일
    private int parent_no; //부모글번호
    private int score;//리뷰 점수
    private String img_url;//이미지경로
    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user_id;


}
