package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter
@Setter
@Entity
@Table(name="tables")
public class Tab {

//    @EmbeddedId
//    private TabId id;
    @Id
    @Column(length = 12)
    private String id;

    @Column(name = "seat_qty", length = 2)
    private int seatQty; // 좌석수

    @Column
    private char using; // 사용중인지 확인.

    public class Request{

    }
}
//
//@Getter
//@Setter
//@EqualsAndHashCode
//@Embeddable
//public class TabId implements Serializable{
//
//    @Column(length = 2, name="tab_id")
//    private int id ; // 좌석번호
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="shop_id")
//    private Shop shop_id; // 매장번호
//
//    public TabId(){}
//}