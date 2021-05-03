package com.jumanji.capston;

import com.jumanji.capston.data.externalData.iamport.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.Scanner;

// 오류 목록
//The HTTP request to initiate the WebSocket connection to [ws://172.26.2.109:8088/ws-stomp/websocket] failed
// 해결 방법 :w
@Slf4j
class StompClientTest {
    public static void main(String[] args) { //            실제 실행중인 서버 주소를 써줘야 함??? ㅁㄹ localhost 안됨?
        StompChatClient client = new StompChatClient("ws://172.26.2.109:8088/ws-stomp/websocket", "/sub/chat/1");
        client.connect();

        while (true) {
            String line = new Scanner(System.in).nextLine();

            try {
                String[] commands = line.split(":");
//                Integer roomSeq = Integer.parseInt(commands[0]);
//                String message = commands[1];
                Integer roomSeq = 1;
                String message = line;

                client.send("/pub/chat", new ChatMessage(roomSeq, message));
            } catch (Exception e) {
                log.error("error", e);
            }
        }
    }

    @RequiredArgsConstructor
    private static class StompChatClient extends StompSessionHandlerAdapter {
        private final String url;
        private final String subscribe;
        private StompSession session;

        @Override
        public Type getPayloadType(StompHeaders headers) {
            return ChatMessage.class;
        }

        @Override
        public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
            this.session = session;
            this.session.subscribe(subscribe, this);
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            ChatMessage chatMessage = (ChatMessage) payload;
            log.info("receive : {}", chatMessage);
        }

        public void connect() {
            WebSocketHttpHeaders httpHeaders = new WebSocketHttpHeaders();

            StompHeaders stompHeaders = new StompHeaders();
            stompHeaders.add("auth-token", "spring-chat-auth-token");

            WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
            stompClient.setMessageConverter(new MappingJackson2MessageConverter());
            stompClient.connect(url, httpHeaders, stompHeaders, this);
        }

        public void send(String destination, ChatMessage chatMessage) {
            session.send(destination, chatMessage);
        }
    }
}