package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.Bucket;
import com.jumanji.capston.data.Chatbot;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface ChatbotService {
    public ResponseEntity<?> get(String chatbotId);
    public ResponseEntity<?> post(Chatbot.Request request);
    public ResponseEntity<?> patch(Chatbot.Request request);
    public ResponseEntity<?> delete(String chatbotId);
}
