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
    @Column(name="odr_id",length=16)
    private String odr_id;//주문번호
    @Column(length=2)
    private int odr_qty;//메뉴수량
    @Column
    private Timestamp odr_date;//주문일시
    @Column(length=60)
    private String odr_req;//요청사항
    @ManyToOne
    @JoinColumn(name="shop")
    private Shop s_id;//매장번호
    @ManyToOne
    @JoinColumn(name="member")
    private Member mem_id;//아이디
    @ManyToOne
    @JoinColumn(name="menu")
    private Menu menu_id;//메뉴번호
    @ManyToOne
    @JoinColumn(name="tab")
    private Tab tab_id;//좌석번호
}
