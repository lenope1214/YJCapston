package com.jumanji.capston.data;

import com.jumanji.capston.data.Shop;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="chatbot")
public class Chatbot {
    @Id
    @Column(name="bot_id",length=20)
    private int bot_id;
    @Column(name="question",length=250,nullable = false)
    private String question;
    @Column(name="answer",length=250,nullable = false)
    private String answer;
    @ManyToOne
    @JoinColumn(name="shop_id",nullable = false)
    private Shop shop_id;

}
