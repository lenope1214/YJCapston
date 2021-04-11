package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.Chatbot;
import org.springframework.http.ResponseEntity;

public interface ChatbotService {
    public ResponseEntity<?> get(String chatbotId);
    public ResponseEntity<?> post(Chatbot.Request request);
    public ResponseEntity<?> patch(Chatbot.Request request);
    public ResponseEntity<?> delete(String chatbotId);
}
