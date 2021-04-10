package com.jumanji.capston.service;

import com.jumanji.capston.data.Message;
import com.jumanji.capston.data.MessageId;
import com.jumanji.capston.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl {
    @Autowired
    MessageRepository messageRepository;

    public Message findById(MessageId id){
        return messageRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("id를 확인해주세요!!!"));
    }


    public Message insert(Message _message){
        return messageRepository.save(_message);
    }

    public String delete(Message _message){
        Message message  = messageRepository.findById(_message.getId()).orElseThrow(()-> new IllegalArgumentException("id를 확인해주세요!!!"));
        messageRepository.delete(message);
        return "ok";
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }
}
