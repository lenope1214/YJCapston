package com.jumanji.capston.service;

import com.jumanji.capston.controller.exception.ShopException.ShopNotFoundException;
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
                .orElseThrow(()-> new ShopNotFoundException("error-1001", "shop id를 확인해주세요!!!"));
    }


    public Shop insert(Shop shop){
        return shopRepository.save(shop);
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



    public Serializable getShopIntro(String shopId){
//        System.out.println("요청 매장 id : " + shopId );
        if(shopRepository.findById(shopId).isPresent()) {
            return shopRepository.findById(shopId).get().getIntro();
        }
        else{
            System.out.println("매장 id 오류");
            return new IllegalArgumentException("매장Id가 잘못되었습니다.");
        }
    }

    public List<Shop> haveShop(String id){
        List<Shop> shopList = shopRepository.findByOwner_Id(id);
        if(shopList.size() == 0)return null;
        return shopRepository.findByOwner_Id(id);
    }

    public List<Shop> findByCat(String category) {
        return shopRepository.findByCategory(category);
    }

    public char updateIsOpen(Shop shop){
        if(shop.getIsOpen() == 'Y')shop.setIsOpen('N');
        else shop.setIsOpen('Y');
        shopRepository.save(shop);
        return shop.getIsOpen();
    }

    public char updateIsRsPos(Shop shop){
        if(shop.getIsRsPos() == 'Y')shop.setIsRsPos('N');
        else shop.setIsRsPos('Y');
        shopRepository.save(shop);
        return shop.getIsRsPos();
    }

    public List<Shop> findByOwnerId(String userId) {
        return shopRepository.findByOwner_Id(userId);
    }
}
