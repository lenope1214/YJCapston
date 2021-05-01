package com.jumanji.capston.config.chat;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final WebSocketInterceptor webSocketInterceptor;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/sub");
        config.setApplicationDestinationPrefixes("/pub");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 연결 URL : ws://localhost:port/ws-stomp/websocket
        registry.addEndpoint("/ws-stomp")
                .setAllowedOrigins("**") // react 포트가 3000이라서 3000으로 뒀었음. 모든 포트 허용 : **
                .withSockJS();
        // withSockJs 사용시의 장점
        // wsebsocket 형태로 ㅂ연결이 불가능한 경우 http를 사용해서 연결이 지속되도록 만든다는 뜻.
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(webSocketInterceptor);
    }
}