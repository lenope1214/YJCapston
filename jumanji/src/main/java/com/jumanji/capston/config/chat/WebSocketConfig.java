package com.jumanji.capston.config.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

import java.util.List;

/**
 * Stomp는 메세지 전송을 효율적으로 하기 위해 나온 프로토콜이며
 * 기본적으로 pub/sub구조로 되어있어 메세지를 발송하고,
 * 메세지를 받아 처리하는 부분이 확실히 정해져 있기 때문에
 * 개발하는 입장에서 명확하게 인지하고 개발할 수 있는 이점이 있습니다.
 * 또한 Stomp를 이용하면 통신 메세지의 헤더에 값을 세팅할 수 있어
 * 헤더 값을 기반으로 통신시 인증처리를 구현하는 것도 가능합니다.
 * pub/sub이란 메시지를 공급하는 주체와 소비하는 주체를 분리하여 제공하는 메시징 방법입니다.
 * 기본적인 콘셉트를 예로 들면 우체통(topic)이 있으면 집배원(publisher)이 신문을
 * 우체통에 배달하는 액션이 있고, 우체통에 신문이 배달되는 것을 기다렸다가 빼서 보는
 * 구독자(subscriber)의 액션이 있습니다. 여기서 구독자는 여러명이 될 수 있습니다.
 * pub/sub 콘셉트를 채팅룸에 대입하면 다음과 같습니다.
 * <p>
 * - 채팅방을 생성한다 : pub/sub 구현을 위한 Topic이 하나 생성된다.
 * - 채팅방에 입장한다 : Topic을 구독한다.
 * - 메세지를 보낸다   : 해당 Topic으로 메시지를 발송
 * - 메세지를 받는다  : 해당 Topic으로 온 메시지를 받는다.
 */


@RequiredArgsConstructor // final auto DI
@Configuration // config bean 등록
@EnableWebSocket // 웹 소켓을 사용하기 위해 선언.
@EnableWebSocketMessageBroker // stomp를 사용하기 위해 선언.
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final WebSocketHandler webSocketHandler;

    @Override // 한 클라이언트에서 다른 클라이언트로 메세지를 라우팅 할때 사용하는 브로커.

    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/sub");
        config.setApplicationDestinationPrefixes("/pub");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp").setAllowedOrigins("*")
                .withSockJS();
    }


    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {

    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {

    }

    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {

    }

    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        return false;
    }

}