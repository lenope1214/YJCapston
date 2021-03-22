package com.jumanji.capston.service;

import com.jumanji.capston.DTO.PutShopDTO;
import com.jumanji.capston.data.Shop;
import com.jumanji.capston.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {
    @Autowired
    ShopRepository shopRepository;

    public Shop findById(String id){
        return shopRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("id를 확인해주세요!!!"));
    }


    public Shop insert(Shop shop){
        System.out.println(shop.getOwner_id());
        shopRepository.save(shop);
        return shop;
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

    public Shop putShop(PutShopDTO putShopDTO) {
        Shop shop = shopRepository.findById(putShopDTO.getId())
                .orElseThrow(()-> new IllegalAccessError("shop_id를 확인해 주세요!!!"));
        shop = putShopDTO.getShop();
        return shopRepository.save(shop);
    }
}
