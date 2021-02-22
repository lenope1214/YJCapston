package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@Entity
@javax.persistence.Table(name="tab")
public class Table implements Serializable {
    @Id
    @Column(length = 16)
    private String id ; // 좌석번호
    @Id
    @ManyToOne
    @JoinColumn(name="s_id")
    private Shop s_id; // 매장번호
    @Column(length = 2, nullable = false)
    private int tab_limit; // 좌석수
}
