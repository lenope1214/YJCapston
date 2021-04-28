package com.jumanji.capston.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


@Getter
@Entity
@Table(name = "ORDER_MENUS")
@NoArgsConstructor
public class OrderMenu implements Serializable {
    @Id
    private String id; // cartId - timestamp(13) + orderList (2) => (15)

    @Column(length = 2)
    private int quantity; // 메뉴 수량

    private char using = 'N';

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
        this.using = 'Y';
    }

    @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        private String orderMenuId;
        private Timestamp orderId;
        private String shopId;
        private String menuName;
        private int quantity;
        private String tabNo; // 테이블번호 : 사업자번호 + 테이블번호 ( 2 )
    }
    @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class RequestList{
        List<Request> list;
    }

    @Getter
    public static class Response{
        private String orderMenuId;
        private String shopId;
        private int quantity;
        private String menuName;
        private String tableNo;

        public Response(OrderMenu order){
            if(order.getId() != null)this.orderMenuId = order.getId();
            this.quantity = order.getQuantity();
            this.shopId = order.getMenu().getId().substring(0, 10);
            this.menuName = order.getMenu().getId().substring(10);
            this.tableNo = order.getTab().getId().substring(10);
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