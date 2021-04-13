package com.jumanji.capston.data;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name="chatbots")
public class Chatbot {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(length=250)
    private String question;
    @Column(length=250)
    private String answer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Shop shop;

    public class Request {
    }
}
