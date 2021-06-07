package com.jumanji.capston.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter @NoArgsConstructor
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

    @Builder
    public Chatbot(String question, String answer, Shop shop){
        this.question = question;
        this.answer = answer;
        this.shop = shop;
    }

    public void update(Request request) {
        this.question = request.getQuestion();
        this.answer = request.getAnswer();
    }

    @Data
    public static class Request {
        private Long chatbotId;
        private String question;
        private String answer;
        private String shopId;
    }

    @Data
    public static class Response {
        private Long chatbotId;
        private String question;
        private String answer;

        public Response(Chatbot chatbot) {
            this.chatbotId = chatbot.getId();
            this.question = chatbot.getQuestion();
            this.answer = chatbot.getAnswer();
        }
    }
}
