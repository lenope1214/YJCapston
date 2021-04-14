package com.jumanji.capston.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Getter
@Entity
@Table(name = "ORDER_MENUS")
@NoArgsConstructor
public class OrderMenu implements Serializable {
    @Id
    private String id; // cartId - timestamp(13) + orderList (2) => (15)

    @Column(length = 2)
    private int quantity; // 메뉴 수량

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    @JsonIgnore
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tab_id")
    @JsonIgnore
    private Tab tab;

    @Builder
    public OrderMenu(String id, int quantity, Menu menu, Tab tab) {
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
        private String shopId;
        private String menu;
        private String tab;

        public Response(OrderMenu order){
            if(order.getId() != null)this.orderId = order.getId();
            this.quantity = order.getQuantity();
            this.shopId = order.getMenu().getId().substring(0, 10);
            this.menu = order.getMenu().getId().substring(10);
            this.tab = order.getTab().getId().substring(10);
        }
    }

    public void patch(OrderMenu order){
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