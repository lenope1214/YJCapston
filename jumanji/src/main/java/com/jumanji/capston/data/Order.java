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
@Table(name="order")
public class Order implements Serializable {
    @Id
    @Column(name="order_id", length = 16)
    private String no ; // 주문번호 insert 할때 값 yyMMddhhmmss+sequence 설정해주기. 기본값으로 하는거 어렵넹
    @Column(length = 2, nullable = false)
    private int order_qty; // 메뉴 수량
    @Column(nullable = false)
    private Date order_date; // 주문일시
    @Column(length = 60)
    private String order_req; // 요청사항
    @JoinColumn
    @ManyToOne
    private Shop shop_id;
    @JoinColumn
    @ManyToOne
    private Member mem_id;
    @JoinColumn
    @ManyToOne
    private Menu menu_id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "tab_id", referencedColumnName = "tab_id"),
            @JoinColumn(name = "shop_id", referencedColumnName = "shop_id")

    })
    private Tab tab_id;
}
