package com.jumanji.capston.config.chat.exam01;

/**
 * Created by lsb123@g.yju.ac.kr on 2021-04-15
 * Blog : https://mylearning.tistory.com/
 * Github : https://github.com/LEESEONGBOK1214
 */

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;
// import 생략....

@Repository
public class ChatRoomRepository {

    private Map<String, ChatRoom> chatRoomMap;

    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        // 채팅방 생성순서 최근 순으로 반환
        List chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    public ChatRoom findRoomById(String id) {
        return chatRoomMap.get(id);
    }

    public ChatRoom createChatRoom(String name) {
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }
}