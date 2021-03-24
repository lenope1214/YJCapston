package com.jumanji.capston.service;

import com.jumanji.capston.Payload.Request.PutShopReqeuest;
import com.jumanji.capston.Payload.Request.shopIntroRequest;
import com.jumanji.capston.data.Shop;
import com.jumanji.capston.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class ShopService {
    @Autowired
    ShopRepository shopRepository;

    public Shop findById(String id){
        return shopRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("id를 확인해주세요!!!"));
    }


    public Object insert(Shop shop, String ownerId){
        System.out.println("매장 생성자 : " + ownerId);
        if(shopRepository.findById(shop.getId()).isPresent()){
            System.out.println("중복이라 일케 보내주잖아 ㅡㅡ");
            return "duplicate";
        }
        System.out.println("저장 전");
        Shop shopEntity = shopRepository.save(shop);
        System.out.println("저장 후");
        System.out.println("저장 : " + shopEntity);
        return shopEntity;
    }

    public String delete(String id){
        System.out.println("입력 id : " + id);
        Shop shop = shopRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("id를 확인해주세요!!!"));
        shopRepository.delete(shop);
        return "ok";
    }

    public List<Shop> findAll() {
        return shopRepository.findAll();
    }

    public Shop putShop(PutShopReqeuest putShopReqeuest) {
        Shop shop = shopRepository.findById(putShopReqeuest.getId())
                .orElseThrow(()-> new IllegalAccessError("shop_id를 확인해 주세요!!!"));
        shop = putShopReqeuest.getShop();
        return shopRepository.save(shop);
    }

    public Serializable getShopIntro(shopIntroRequest req){
        System.out.println("요청 매장 id : " + req.getShopId() );
        if(shopRepository.findById(req.getShopId()).isPresent()) {

            return shopRepository.findById(req.getShopId()).get().getIntro();
        }
        else{
            System.out.println("매장 id 오류");
            return new IllegalArgumentException("매장Id가 잘못되었습니다.");
        }
    }
}
