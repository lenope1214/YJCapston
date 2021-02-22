package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name="tab")
public class Tab implements Serializable {
    @Id
    @Column(name="tab_id",length=2)
    private int tab_id;//좌석번호
    @Id
    @ManyToOne
    @JoinColumn(name="shop")
    private Shop s_id;//매장번호
    @Column(length=2)
    private int tab_limit;//좌석수
}
