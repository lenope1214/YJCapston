package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name="odr")
public class Odr {
    @Id
    @Column(name="odrer_id",length=16)
    private String odr_id;//주문번호
    @Column(length=2)
    private int odr_qty;//메뉴수량
    @Column
    private Timestamp odr_date;//주문일시
    @Column(length=60)
    private String odr_req;//요청사항
    @ManyToOne
    @JoinColumn(name="shop_id",nullable = false)
    private Shop s_id;//매장번호
    @ManyToOne
    @JoinColumn(name="member_id",nullable = false)
    private Member mem_id;//아이디
    @ManyToOne
    @JoinColumn(name="menu_id",nullable = false)
    private Menu menu;//메뉴번호
    @ManyToOne
    @JoinColumn(name="tab_id",nullable = false)
    private Tab tab;//좌석번호
}
