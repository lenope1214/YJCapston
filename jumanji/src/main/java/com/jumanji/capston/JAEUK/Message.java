package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name="message")
public class Message {
    @Id
    @Column(length=12,nullable = false)
    private int msg_no;//번호
    @Column(nullable = false)
    private Timestamp msg_time;//시간
    @Column(length=200,nullable = false)
    private String msg_content;//내용
    @ManyToOne
    @JoinColumn(name="account",nullable = false)
    private Account id;
    @ManyToOne
    @JoinColumn(name="shop",nullable = false)
    private Shop s_id;
}
