package com.jumanji.capston.controller.chatting;

import com.jumanji.capston.data.StompMessage;
//import com.jumanji.capston.service.stomp.StompService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@RestController
public class
ChatController {
    private static final Set<String> SESSION_IDS = new HashSet<>();
    private final SimpMessagingTemplate messagingTemplate;
//    private final StompService stompService;

    @MessageMapping("/chat") // "/pub/chat"
    public void publishChat(StompMessage chatMessage) {
        System.out.println("chat>>>>>>>>");
        log.info("publishChat : {}", chatMessage);

        messagingTemplate.convertAndSend("/sub/" + chatMessage.getShopId() + "/" + chatMessage.getType() + "/" + chatMessage.getRoomId(), chatMessage);
    }

    @MessageMapping("/pub/chat") // "/pub/chat"
    public void publishChat2(StompMessage chatMessage) {
        System.out.println("pub/chat>>>>>>>>");
        log.info("publishChat : {}", chatMessage);

        messagingTemplate.convertAndSend("/sub/" + chatMessage.getShopId() + "/" + chatMessage.getType() + "/" + chatMessage.getRoomId(), chatMessage);
    }

    @MessageMapping("/jmj") // "/pub/jmj"
    public void publishChat3(String chatMessage) {
        System.out.println("pub/jmj>>>>>>>>");
        log.info("publishChat : {}", chatMessage);

        messagingTemplate.convertAndSend("/sub/4850101906/o/roomId", chatMessage);
    }

    @MessageMapping("/test/chat")
    @SendTo("/sub/test/chat")
    public StompMessage testChat(StompMessage stompMessage){
        System.out.println(stompMessage.toString());
        return stompMessage;
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