package com.jumanji.capston.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@Entity
@Table (name="tables")
class Tab {

    @EmbeddedId
    private TabId tab_id;

    private int seat_qty; // 좌석수
}

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class TabId implements Serializable{

    @Column(length = 16, name="tab_id")
    private String id ; // 좌석번호

    @ManyToOne
    @JoinColumn(name="tab_shop_id")
    private Shop shop_id; // 매장번호

    public TabId(){}
}