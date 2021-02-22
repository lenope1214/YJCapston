package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="chatbot")
public class Chatbot {
    @Id
    @Column(length=12,nullable = false)
    private int bot_no;
    @Column(length=100,nullable = false)
    private String bot_question;
    @Column(length=100,nullable = false)
    private String bot_answer;
    @ManyToOne
    @JoinColumn(name="shop",nullable = false)
    private Shop s_id;

}
