package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Getter
@Setter
@Entity
@javax.persistence.Table(name="tables")
class Tab {

//    @EmbeddedId
//    private TabId id;
    @Id
    @Column(length = 12)
    private String id;

    @Column(length = 2)
    private int seat_qty; // 좌석수
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
//    @ManyToOne
//    @JoinColumn(name="shop_id")
//    private Shop shop_id; // 매장번호
//
//    public TabId(){}
//}