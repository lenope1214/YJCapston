package com.jumanji.capston.controller;

import com.jumanji.capston.data.Chatbot;
import com.jumanji.capston.service.ChatbotServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class ChatbotController  {
    private final ChatbotServiceImpl chatbotService;

    @PostMapping("chatbots")
    public ResponseEntity<?> postChatbot(@RequestHeader String authorization, @RequestBody Chatbot.Request request){
        Chatbot chatbot = chatbotService.post(authorization, request);
        Chatbot.Response response = new Chatbot.Response(chatbot);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
