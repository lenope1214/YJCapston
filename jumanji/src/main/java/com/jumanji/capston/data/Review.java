package com.jumanji.capston.data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Table;
import java.sql.Timestamp;


@Getter
@Setter
@Entity
@Table(name="reviews")
public class Review {
    @Id
    @Column(name = "rev_id", length=5,nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REVIEW_SEQ_GENERATOR")
    @SequenceGenerator(name="REVIEW_SEQ_GENERATOR", sequenceName="REVIEW_SEQ", allocationSize = 1)
    private int id; //리뷰번호
    @ManyToOne
    @JoinColumn(nullable = false)
    private Shop shop_id;//매장번호 !!복합키설정 필요함!!
    @Column(name = "rev_content", length =500)
    private String content;//리뷰내용
    @Column(name = "rev_date")
    private Timestamp date;//등록일
    @Column(name = "rev_p_no", length=4)
    private int p_no; //부모글번호
    @Column(name = "rev_score", length=2,nullable = false)
    private int score;//평점
    @Column(name = "rev_img_url", length=150)
    private String img_url;//이미지경로
    @ManyToOne
    @JoinColumn(name="member_id",nullable = false)
    private User mem_id;


}
