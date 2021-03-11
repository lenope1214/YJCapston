package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@Table(name="chatbots")
public class Chatbot {
    @Id
    private long id;
    @Column(length=100,nullable = false)
    private String question;
    @Column(length=100,nullable = false)
    private String answer;
    @ManyToOne
    @JoinColumn(name="shop_id",nullable = false)
    private Shop shop_id;

}
