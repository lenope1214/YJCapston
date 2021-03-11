package com.jumanji.capston.data;

import com.jumanji.capston.data.Shop;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Table;

@Getter
@Setter
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
