package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name="menu")
public class Menu {
    @Id
    @Column(name="menu_id",length=13)
    private String menu_id;//메뉴번호
    @Column(length=30)
    private String menu_name;//메뉴이름
    @Column(length=100)
    private String menu_content;//메뉴설명
    @Column(length=2)
    private String is_sale;//판매중
    @Column(length=2)
    private String is_popular;//인기메뉴여부
    @Column(length=5)
    private int menu_price;//메뉴가격
    @Column
    private Timestamp menu_dur;//소요시간
    @Column(length=150)
    private String menu_img_url;//이미지경로
}
