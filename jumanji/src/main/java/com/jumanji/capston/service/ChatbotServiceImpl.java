package com.jumanji.capston.service;

import com.jumanji.capston.data.Chatbot;
import com.jumanji.capston.repository.ChatbotRepository;
import com.jumanji.capston.service.interfaces.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public class ChatbotServiceImpl implements ChatbotService {

    @Autowired
    ChatbotRepository chatbotRepository;

    public Chatbot findById(Long id){
        return chatbotRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("챗봇 id를 확인해주세요!!!"));
    }


    public Chatbot insert(Chatbot _chatbot){
        return chatbotRepository.save(_chatbot);
    }

    public String delete(Chatbot _chatbot){
        Chatbot chatbot  = chatbotRepository.findById(_chatbot.getId()).orElseThrow(()-> new IllegalArgumentException("챗봇 id를 확인해주세요!!!"));
        chatbotRepository.delete(chatbot);
        return "ok";
    }

    public List<Chatbot> findAll() {
        return chatbotRepository.findAll();
    }

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
}
