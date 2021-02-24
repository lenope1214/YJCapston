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
    @Column(name="msg_no",length=12,nullable = false)
    private int msg;//번호
    @Column(nullable = false)
    private Timestamp msg_time;//시간
    @Column(length=200,nullable = false)
    private String msg_content;//내용
    @ManyToOne
    @JoinColumn(name="member_id",nullable = false)
    private Member mem_id;//아이디
    @ManyToOne
    @JoinColumn(name="shop_id",nullable = false)
    private Shop s_id;//매장번호
}
