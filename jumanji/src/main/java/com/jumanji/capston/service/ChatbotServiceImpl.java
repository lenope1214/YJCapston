package com.jumanji.capston.service;

import com.jumanji.capston.data.Chatbot;
import com.jumanji.capston.repository.ChatbotRepository;
import com.jumanji.capston.service.interfaces.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.List;

@Service
public class ChatbotServiceImpl implements BasicService<Chatbot, Chatbot.Request> {

    @Autowired
    ChatbotRepository chatbotRepository;


    @Override
    public Chatbot get(@Nullable String authorization, String... str) {
        return null;
    }

    @Override
    public List<Chatbot> getList(@Nullable String authorization, String... str) {
        return null;
    }

    @Override
    public Chatbot post(@Nullable String authorization, Chatbot.Request request) {
        return null;
    }

    @Override
    public Chatbot patch(@Nullable String authorization, Chatbot.Request request) {
        return null;
    }

    @Override
    public void delete(@Nullable String authorization, String... str) {

    }

    @Override
    public Chatbot isPresent(String id) {
        return null;
    }

    @Override
    public boolean isEmpty(String id) {
        return false;
    }
}
