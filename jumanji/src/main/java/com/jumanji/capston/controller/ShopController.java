package com.jumanji.capston.controller;

import com.jumanji.capston.DTO.PutShopDTO;
import com.jumanji.capston.data.Shop;
import com.jumanji.capston.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class ShopController {
    @Autowired
    ShopService shopService;


    @Transactional(readOnly = true)
    @GetMapping("/shopList")
    public ResponseEntity<?> getShopList() {
        List<Shop> shopList = shopService.findAll();
        return new ResponseEntity<>(shopList, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/shop")
    public ResponseEntity<?> insertShop(@RequestBody Shop shop) {
        if (shopService.insert(shop) != null) return new ResponseEntity<>(shop, HttpStatus.CREATED);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Transactional
    @DeleteMapping("/shop")
    public ResponseEntity<?> deleteShop(@RequestBody String id) {
        return new ResponseEntity<>(shopService.delete(id), HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/shop")
    public ResponseEntity<?> putShop(@RequestBody PutShopDTO putShopDTO){
        return new ResponseEntity<>(shopService.putShop(putShopDTO), HttpStatus.OK);
    }
}
