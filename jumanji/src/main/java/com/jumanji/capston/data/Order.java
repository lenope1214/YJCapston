package com.jumanji.capston.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order implements Serializable {
    @Id
    private String id; // cartId - timestamp(13) + orderList (2) => (15)

    @Column(length = 2)
    private int quantity; // 메뉴 수량

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tab_id")
    private Tab tab;

    @Builder
    public Order(String id, int quantity, Menu menu, Tab tab) {
        this.id = id;
        this.quantity = quantity;
        this.menu = menu;
        this.tab = tab;
    }

    @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        private String orderId;
        private Timestamp cartId;
        private int quantity;
        private String menuId;
        private String tabId; // 테이블번호 : 사업자번호 + 테이블번호 ( 2 )
    }

    @Getter
    public static class Response{
        private String orderId;
        private int quantity;
        private String menuId;
        private String tabId;

        public Response(Order order){
            if(order.getId() != null)this.orderId = order.getId();
            this.quantity = order.getQuantity();
            this.menuId = order.getMenu().getId();
            this.tabId = order.getTab().getId();
        }
    }

    public void patch(Order order){
        if(order.getQuantity() != 0)this.quantity = order.getQuantity();
        if(order.getTab() != null)this.tab = order.getTab();
        if(order.getMenu() != null)this.menu = order.getMenu();
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