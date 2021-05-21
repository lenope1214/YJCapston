package com.jumanji.capston.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@Table(name = "ORDER_MENUS")
@NoArgsConstructor @ToString
public class OrderMenu implements Serializable {
    @Id
    private String id; // orderId timestamp(13) + orderList (2) => (15)

    @Column(length = 2)
    private int quantity; // 메뉴 수량

//    private char using = 'N'; // Y, N, R(예, 아니요, 예약)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    @JsonIgnore
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tab_id")
    @JsonIgnore
    private Tab tab;

    @JsonIgnore @Transient @Setter
    private List<OrderMenuOption> optionList;

    @Builder
    public OrderMenu(String id, int quantity, Menu menu, Tab tab) {
        this.id = id;
        this.quantity = quantity;
        this.menu = menu;
        this.tab = tab;
//        this.using = 'Y';
    }

    @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        private String orderMenuId;
        private Timestamp orderId;
        private String shopId;
        private String menuId;
        private int quantity;
        private int tabNo; // 테이블번호 : 사업자번호 + 테이블번호 ( 2 )
        private List<OrderMenuOption.Request> optionList;
    }
    @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class RequestList{
        List<Request> list;
    }

    @Getter @AllArgsConstructor
    public static class Response{
        private String orderMenuId;
        private String shopId;
        private int quantity;
        private String menuName;
        private String tableNo;
        private List<OrderMenuOption.Response> optionList = new ArrayList<>();


        public Response(OrderMenu order){
            optionList = new ArrayList<>();

            if(order.getId() != null)this.orderMenuId = order.getId();
            this.quantity = order.getQuantity();
            this.shopId = order.getMenu().getId().substring(0, 10);
            this.menuName = order.getMenu().getId().substring(10);
            if(order.getTab()!=null)this.tableNo = order.getTab().getId().substring(10);
            if(order.optionList == null)return;
            for(OrderMenuOption omo : order.optionList){
                this.optionList.add(new OrderMenuOption.Response(omo));
            }
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