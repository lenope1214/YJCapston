package com.jumanji.capston.config.chat;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import javax.security.auth.message.AuthException;

@Slf4j
@Component
public class WebSocketInterceptor implements ChannelInterceptor {
    @SneakyThrows
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (accessor.getCommand() == StompCommand.CONNECT) {
            System.out.println("스톰프 커맨드 커넥트.");
//            String authToken = accessor.getFirstNativeHeader("socket_token");
//
//            if (!"jmj-chatting".equals(authToken)) {
//                throw new AuthException("fail");
//            }
        }

        return message;
    }
}
