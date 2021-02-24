package com.jumanji.capston.data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Table;
import java.sql.Timestamp;


@Getter
@Setter
@Entity
@Table(name="review")
public class Review {
    @Id
    @Column( length=5,nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REVIEW_SEQ_GENERATOR")
    @SequenceGenerator(name="REVIEW_SEQ_GENERATOR", sequenceName="REVIEW_SEQ", allocationSize = 1)
    private int re_no; //리뷰번호

    @Column( length=10,nullable = false)
    private String s_id;//매장번호 !!복합키설정 필요함!!
    @Column( length =500)
    private String re_centent;//리뷰내용
    @Column
    private Timestamp re_date;//등록일
    @Column(length=4)
    private int re_p_no; //부모글번호
    @Column(length=2,nullable = false)
    private int re_score;//평점
    @Column(length=150)
    private String re_img;//이미지경로
    @ManyToOne
    @JoinColumn(name="account",nullable = false)
    private Account id;


}
