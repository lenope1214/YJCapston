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
@Table(name="ordr")
public class Order implements Serializable {
    @Id
    @Column(name="order_id", length = 16)
    private String id ; // 주문번호 insert 할때 값 yyMMddhhmmss+sequence 설정해주기. 기본값으로 하는거 어렵넹

    @Column(name="quantity", length = 2, nullable = false)
    private int quantity; // 메뉴 수량
    @Column(name="order_time", nullable = false)
    private Date date; // 주문일시
    @Column(name="order_req", length = 60)
    private String request; // 요청사항

    @ManyToOne
    @JoinColumn(name = "ordr_shop_id", insertable = false, updatable = false)
    private Shop shop_id;

    @ManyToOne
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    private User member_id;

    @Embedded
    private MenuId menu_id;

    @Embedded // 비 식별 관계 식별관계로 하려면 @EmbeddedId로 변경
    private TabId tab_id;
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