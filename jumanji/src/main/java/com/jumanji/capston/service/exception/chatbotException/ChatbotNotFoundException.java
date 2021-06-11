package com.jumanji.capston.service.exception.chatbotException;

import com.jumanji.capston.service.exception.BasicException;

public class ChatbotNotFoundException extends BasicException {
    public ChatbotNotFoundException(){
        super("error-1201", "Chatbot Not Found !");
    }

    public ChatbotNotFoundException(String id){
        super("error-1201", "Chatbot Not Found ! : " + id);
    }

    public ChatbotNotFoundException(String code, String message) {
        super(code, message);
    }


}