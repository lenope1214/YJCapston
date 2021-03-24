package com.jumanji.capston.controller;

import com.jumanji.capston.Payload.Request.PutShopReqeuest;
import com.jumanji.capston.Payload.Request.shopIntroRequest;
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
    @PostMapping("/shop") // 매장등록
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
    public ResponseEntity<?> putShop(@RequestBody PutShopReqeuest putShopReqeuest){
        return new ResponseEntity<>(shopService.putShop(putShopReqeuest), HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/shopIntro")
    public ResponseEntity<?> getShopIntro(@RequestBody shopIntroRequest req){
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Content-Type", "text/html; charset=UTF-8");
//        httpHeaders
        return new ResponseEntity<>(shopService.getShopIntro(req), HttpStatus.OK);
    }

}
