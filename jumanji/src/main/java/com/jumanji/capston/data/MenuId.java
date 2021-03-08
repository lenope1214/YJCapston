package com.jumanji.capston.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Table;
import java.awt.*;
import java.io.Serializable;
import java.util.Date;

/*
last update 2021-02-23
author 이성복
 */

@Getter
@Setter
@Entity
@Table(name="menu")
class Menu{

    @EmbeddedId
    private MenuId id; // 메뉴번호
    
    @Column(name="name", length = 90, nullable = false)
    private String name; // 메뉴이름
    @Column(name="description", length = 100,nullable = false)
    private String description; //메뉴설명
    @Column(name="is_sale")
    private char is_sale = 'Y'; // 판매중
    @Column(name="is_popular")
    private char is_popular = 'N'; // 인기메뉴여부
    @Column(name="price",length = 5, nullable = false)
    private int price = 0; // 가격
    @Column(name="duration",length=2)
    private int duration; // 소요시간
    @Column(name="img_url", length = 250)
    private String img_url; // 이미지 상대 경로
}


@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class MenuId  implements Serializable {
    
    @Column(name="menu_id", length = 3)
    private int menu_id ; // 메뉴번호
    
    @ManyToOne
    @JoinColumn(name="menu_shop_id",nullable = false)
    private Shop shop_id;//매장번호
    
    public MenuId(){}
}