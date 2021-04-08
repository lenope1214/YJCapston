package com.jumanji.capston.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Getter
@Entity
@Table(name="orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    @Column(insertable = false, updatable = false)
    private Long id ; // 주문번호 insert 할때 값 sequence 설정해주기. 기본값으로 하는거 어렵넹

    @Column(length = 2)
    private int quantity; // 메뉴 수량
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(name="order_time")
    private Date orderTime; // 주문일시
    @Column(name="order_request")
    private String orderRequest; // 요청사항

    @ManyToOne
    @JoinColumn(name = "shop_id", insertable = false, updatable = false)
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="menu_id")
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "tab_id", insertable = false, updatable = false)
    private Tab tab;

    @Getter
    public
    class Request{
        private int quantity;
        private String orderRequest;
//        private Date orderTime;
        private String shopId;
        private String userId;
        private String menuId;
        private String tabId; // 테이블번호 : 사업자번호 + 테이블번호 ( 2 )

        public Request(Order order){

        }
    }

    @Builder
    public Order(Long id, int quantity, String orderRequest, Shop shop, User user, Menu menu, Tab tab) {
        this.id = id;
        this.quantity = quantity;
        this.orderRequest = orderRequest;
        this.orderTime = new Date();
        this.shop = shop;
        this.user = user;
        this.menu = menu;
        this.tab = tab;
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