package com.jumanji.capston.data;

import lombok.Getter;
import oracle.sql.TIMESTAMP;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Entity
@Table(name="orders")
public class Order implements Serializable {
    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    @Column(insertable = false, updatable = false)
    private Long id ; // 주문번호 insert 할때 값 yyMMddhhmmss+sequence 설정해주기. 기본값으로 하는거 어렵넹

    @Column(length = 2)
    private int quantity; // 메뉴 수량
    @Column(name="order_time")
    private TIMESTAMP orderTime; // 주문일시
    private String request; // 요청사항

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="menu_id")
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "tab_id", insertable = false, updatable = false)
    private Tab table;

    @Getter
    class Request{
        private Long id;
    }
}

//@Getter
//@Setter
//@Embeddable
//@EqualsAndHashCode
//public class shop_menu_tab implements Serializable {
//
//    @Column(name="menu_id", length = 3)
//    private int menu_id ; // 메뉴번호
//
//    @Embedded
//    private TabId tab_id;
//    public shop_menu_tab(){}
//}