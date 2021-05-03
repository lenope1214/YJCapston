package com.jumanji.capston.controller.chatting;

import com.jumanji.capston.data.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ChatController {
    private static final Set<String> SESSION_IDS = new HashSet<>();
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat") // "/pub/chat"
    public void publishChat(ChatMessage chatMessage) {
        log.info("publishChat : {}", chatMessage);
//        if(chatMessage.getMessage().contains(":")){
//            chatMessage.setUsername(chatMessage.getMessage().substring(0, chatMessage.getMessage().indexOf(":")));
//            chatMessage.setMessage(chatMessage.getMessage().substring(chatMessage.getMessage().indexOf(":")));
//        }
        messagingTemplate.convertAndSend("/sub/chat/" + chatMessage.getRoomSeq(), chatMessage);
    }

    @EventListener(SessionConnectEvent.class)
    public void onConnect(SessionConnectEvent event) {
        String sessionId = event.getMessage().getHeaders().get("simpSessionId").toString();
        SESSION_IDS.add(sessionId);
        log.info("[connect] connections : {}", SESSION_IDS.size());
    }

    @EventListener(SessionDisconnectEvent.class)
    public void onDisconnect(SessionDisconnectEvent event) {
        String sessionId = event.getSessionId();
        SESSION_IDS.remove(sessionId);
        log.info("[disconnect] connections : {}", SESSION_IDS.size());
    }
}