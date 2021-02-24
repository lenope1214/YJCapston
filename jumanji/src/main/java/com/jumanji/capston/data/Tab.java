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
@Table (name="tab")
public class Tab implements Serializable {
    @Id
    @Column(length = 16, name="tab_id")
    private String id ; // 좌석번호
    @Id
    @ManyToOne
    @JoinColumn(name="shop_id")
    private Shop shop_id; // 매장번호
    @Column(length = 2, nullable = false)
    private int tab_limit; // 좌석수
}
