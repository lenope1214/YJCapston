package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name="odr")
public class Menu implements Serializable {
    @Id
    @Column(name="m_no", length = 3)
    private String no ; // 메뉴번호
    @Id
    @ManyToOne
    @JoinColumn(name="s_id")
    private Shop s_id; // 매장번호

    @Column(name="m_name", length = 30, nullable = false)
    private String name; // 이름
    @Column(name="m_content", nullable = false)
    private String content; // 설명
    @Column
    private char is_sale = 'Y'; // 판매중
    @Column
    private char is_popular = 'N'; // 인기메뉴여부
    @Column(name="m_price",length = 5, nullable = false)
    private int price = 0; // 가격
    @Column(name="m_dur")
    private Date duration; // 소요시간
    @Column(name="m_img_url", length = 150)
    private String img_url; // 이미지 상대 경로
}


@Entity
@SequenceGenerator(
        name = "MENU_SEQ_GENERATOR",
        sequenceName = "MENU_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Table(name = "")