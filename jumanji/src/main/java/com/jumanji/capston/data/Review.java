package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name="review")
    public class Review implements Serializable {
    @Id
    @Column(name = "rev_id", length = 20, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REVIEW_SEQ_GENERATOR")
    @SequenceGenerator(name = "REVIEW_SEQ_GENERATOR", sequenceName = "REVIEW_SEQ", allocationSize = 1)
    private int rev_id; //리뷰번호

    @Id
    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop_id;//매장번호
    @Column(name = "content")
    private String content;//리뷰내용
    @Column(name = "reg_date")
    private Timestamp reg_date;//등록일
    @Column(name = "parent_id", length = 20)
    private int parent_id; //부모글번호
    @Column(name = "score", length = 2, nullable = false)
    private int score;//평점
    @Column(length = 150)
    private String re_img;//이미지경로
    @ManyToOne
    @JoinColumn(name="member_id",nullable = false)
    private Member member_id;//아이디
}
