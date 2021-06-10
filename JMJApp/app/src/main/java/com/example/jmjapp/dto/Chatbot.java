package com.example.jmjapp.dto;

import lombok.*;

@Data
@AllArgsConstructor @Builder
@NoArgsConstructor
public class Chatbot {
    private Long chatbotId;
    private String question;
    private String answer;
//    private String shopId;

//    @Builder
//    public Chatbot(String question, String answer, Shop shop){
//        this.question = question;
//        this.answer = answer;
//        this.shop = shop;
//    }
//
//    @Data
//    public static class Request {
//        private String question;
//        private String answer;
//        private String shopId;
//    }
//
//    @Data
//    public static class Response {
//        private String question;
//        private String answer;
//
//        public Response(Chatbot chatbot) {
//            this.question = chatbot.getQuestion();
//            this.answer = chatbot.getAnswer();
//        }
//    }
}
