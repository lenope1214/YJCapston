package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name="panelty")
public class Panelty {
    @Id
    @Column(name="pnty_id",length = 20)
    private int pnty_id;
    @ManyToOne
    @JoinColumn(name="member_id",nullable = false)
    private Member member_id;//아이디
    @Column(name="pnty_time")
    private Timestamp pnty_time;
    @Column(name="name",length = 60)
    private String name;
}
