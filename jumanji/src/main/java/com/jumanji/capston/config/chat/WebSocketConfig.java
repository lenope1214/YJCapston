//package com.jumanji.capston.config.chat;
//
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//
///**
// * Created by LEESEONGBOK on 2021-04-16.
// */
//@Configurable
//@EnableWebSocket
//@EnableWebSocketMessageBroker
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry config) {
//        config.enableSimpleBroker("/topic");
//    }
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/test").setAllowedOrigins("*").withSockJS();
//    }
//}
//
