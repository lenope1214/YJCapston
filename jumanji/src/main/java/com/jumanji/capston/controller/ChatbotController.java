package com.jumanji.capston.controller;

import com.jumanji.capston.data.Chatbot;
import com.jumanji.capston.service.ChatbotServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class ChatbotController  {
    private final ChatbotServiceImpl chatbotService;

    @GetMapping("shops/{shopId}/chatbots")
    public ResponseEntity<?> getShopsChatbotList(@PathVariable String shopId){
        List<Chatbot> chatbotList = chatbotService.getList(null, shopId);
        List<Object> response = new ArrayList<>();
        for(Chatbot chatbot: chatbotList){
            response.add(new Chatbot.Response(chatbot));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("chatbots")
    public ResponseEntity<?> postChatbot(@RequestHeader String authorization, @RequestBody Chatbot.Request request){
        Chatbot chatbot = chatbotService.post(authorization, request);
        Chatbot.Response response = new Chatbot.Response(chatbot);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
