package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name="messages")
public class Message {
    @Id
    private long id;//번호
    private Timestamp reg_time;//시간
    private String content;//내용
    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user_id;
    @ManyToOne
    @JoinColumn(name="shop_id",nullable = false)
    private Shop shop_id;
}
