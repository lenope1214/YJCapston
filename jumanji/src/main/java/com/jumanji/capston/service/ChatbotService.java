package com.jumanji.capston.service;

import com.jumanji.capston.data.Chatbot;
import com.jumanji.capston.repository.ChatbotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatbotService {

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
}
