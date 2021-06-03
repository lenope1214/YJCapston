package com.jumanji.capston.service;

import com.jumanji.capston.data.Chatbot;
import com.jumanji.capston.data.Shop;
import com.jumanji.capston.repository.ChatbotRepository;
import com.jumanji.capston.service.interfaces.BasicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.persistence.GeneratedValue;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatbotServiceImpl implements BasicService<Chatbot, Chatbot.Request> {
    private final UserServiceImpl userService;
    private final ShopServiceImpl shopService;
    private final ChatbotRepository chatbotRepository;


    @Override
    public Chatbot get(@Nullable String authorization, String... str) {
        return null;
    }

    @Override
    public List<Chatbot> getList(@Nullable String authorization, String... str) {
        String shopId = str[0];
        List<Chatbot> chatbotList = chatbotRepository.findByShop_IdOrderByIdDesc(shopId);
        return chatbotList;
    }

    @Override
    public Chatbot post(@Nullable String authorization, Chatbot.Request request) {
        String loginId = userService.getMyId(authorization);  // 로그인 되어있는지
        Shop shop = shopService.isPresent(request.getShopId()); // 요청 식당이 존재하는 식당인지,
        shopService.isOwnShop(loginId, shop.getId()); // 내 식당이 맞는지
        if(request.getQuestion().isEmpty() || request.getAnswer().isEmpty()){
            throw new NullPointerException("질문과 답을 모두 작성해 주세요.");
        }
        Chatbot chatbot = Chatbot.builder()
                .question(request.getQuestion())
                .answer(request.getAnswer())
                .shop(shop)
                .build();
        chatbotRepository.save(chatbot);
        return chatbot;
    }

    @Override
    public Chatbot patch(@Nullable String authorization, Chatbot.Request request) {
        return null;
    }

    @Override
    public void delete(@Nullable String authorization, String... str) {

    }

    @Override
    public Chatbot isPresent(String id) {
        return null;
    }

    @Override
    public boolean isEmpty(String id) {
        return false;
    }
}
