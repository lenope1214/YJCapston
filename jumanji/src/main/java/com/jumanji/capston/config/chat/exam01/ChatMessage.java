package com.jumanji.capston.config.chat.exam01;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    public enum MessageType{
        ENTER, TALK
    }
    private MessageType type; // 메세지 타입
    private String roomId; // 방 번호
    private String sender; // 보낸사람
    private String message;  // 메세지
}
