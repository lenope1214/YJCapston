package com.jumanji.capston.service.stomp;

import com.jumanji.capston.data.StompMessage;
import com.jumanji.capston.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StompService {
    @Autowired
    MessageRepository messageRepository;

    public void post(StompMessage chatMessage) {
    }


}
