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
    @Column(name = "msg_id", length=20,nullable = false)
    private int msg_id;//번호
    @Column(name="reg_time",nullable = false)
    private Timestamp reg_time;//시간
    @Column(name="content",length=300,nullable = false)
    private String content;//내용
    @ManyToOne
    @JoinColumn(name="member_id",nullable = false)
    private Member member_id;
    @ManyToOne
    @JoinColumn(name="shop_id",nullable = false)
    private Shop shop_id;
}
