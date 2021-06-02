package com.jumanji.capston.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "chatbots")
public class Chatbot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 250)
    private String question;
    @Column(length = 250)
    private String answer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @JsonIgnore
    private Shop shop;

    @Data
    public static class Request {
        private String question;
        private String answer;
        private String shopId;
    }

    @Data
    public static class Response {
        private String question;
        private String answer;

        public Response(Chatbot chatbot) {
            this.question = chatbot.getQuestion();
            this.answer = chatbot.getAnswer();
        }
    }
}
