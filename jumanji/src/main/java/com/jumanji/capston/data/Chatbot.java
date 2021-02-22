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
    @Column(length=12)
    private int bot_no;
    @Column(length=100,nullable = false)
    private String bot_question;
    @Column(length=100,nullable = false)
    private String bot_answer;
    @ManyToOne
    @JoinColumn(name="shop_id",nullable = false)
    private Shop shop_id;

}
