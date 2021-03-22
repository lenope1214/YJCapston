package com.jumanji.capston.service;

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
                .orElseThrow(()-> new IllegalArgumentException("shop_id를 확인해주세요!!!"));
    }


    public Shop insert(Shop shop){
        System.out.println(shop.getOwner_id());
        shopRepository.save(shop);
        return shop;
    }

    public String delete(Shop shop){
        shopRepository.delete(shop);
        return "ok";
    }

    public List<Shop> findAll() {
        return shopRepository.findAll();
    }
}
