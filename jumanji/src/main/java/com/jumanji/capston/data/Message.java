package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name="message")
public class Message {
    @Id
    @Column(length=12,nullable = false)
    private int msg_id;//번호
    @Column(nullable = false)
    private Timestamp msg_time;//시간
    @Column(length=300,nullable = false)
    private String msg_content;//내용
    @ManyToOne
    @JoinColumn(name="acc_id",nullable = false)
    private Account acc_id;
    @ManyToOne
    @JoinColumn(name="shop_id",nullable = false)
    private Shop shop_id;
}
