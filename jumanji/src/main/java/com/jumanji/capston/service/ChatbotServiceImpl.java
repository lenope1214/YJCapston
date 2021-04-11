package com.jumanji.capston.service;

import com.jumanji.capston.data.Chatbot;
import com.jumanji.capston.repository.ChatbotRepository;
import com.jumanji.capston.service.interfaces.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ChatbotServiceImpl implements ChatbotService, BasicService {

    @Autowired
    ChatbotRepository chatbotRepository;


    @Override
    public ResponseEntity<?> get(String chatbotId) {
        return null;
    }

    @Override
    public ResponseEntity<?> post(Chatbot.Request request) {
        return null;
    }

    @Override
    public ResponseEntity<?> patch(Chatbot.Request request) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(String chatbotId) {
        return null;
    }

    @Override
    public boolean isPresent(String id) {
        return false;
    }

    @Override
    public boolean isEmpty(String id) {
        return false;
    }
}
