package com.example.restapi.service;

import com.example.restapi.model.Shop;
import com.example.restapi.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class ShopServiceImpl implements ShopService {
    private ShopRepository shopRepository;

    @Override
    public void register(@RequestBody Map<String, String> map) {
        System.out.println("sericeimpl@@@@@@: " + map);

        Shop shop = dtoToEntity(map);

        shopRepository.save(shop);
    }

}
