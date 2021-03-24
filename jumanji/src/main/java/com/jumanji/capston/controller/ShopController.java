package com.jumanji.capston.controller;

import com.jumanji.capston.Payload.Request.PutShopReqeuest;
import com.jumanji.capston.Payload.Request.shopIntroRequest;
import com.jumanji.capston.data.Shop;
import com.jumanji.capston.data.User;
import com.jumanji.capston.service.ShopService;
import com.jumanji.capston.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class ShopController {
    @Autowired
    ShopService shopService;

    @Autowired
    UserService userService;

    @Autowired
    HttpHeaders httpHeaders;

    @Transactional(readOnly = true)
    @GetMapping("/shopList")
    public ResponseEntity<?> getShopList() {
        List<Shop> shopList = shopService.findAll();
        return new ResponseEntity<>(shopList, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/shop") // 매장등록
    public ResponseEntity<?> insertShop(@RequestBody Shop shop) {
        User userEntity  =  userService.findById(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println("매장등록 요청 ID : " + userEntity.getId());
        Object result = shopService.insert(shop, userEntity.getId());
        if (result.getClass() == Shop.class) return new ResponseEntity<>(result, HttpStatus.CREATED);
        else if(result.equals("duplicate"))return new ResponseEntity<>("사업자 번호가 중복입니다.", httpHeaders, HttpStatus.BAD_REQUEST);
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

    @Transactional(readOnly = true)
    @GetMapping("/shopIntro")
    public ResponseEntity<?> getShopIntro(@RequestBody shopIntroRequest req){
        return new ResponseEntity<>(shopService.getShopIntro(req), httpHeaders, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/myShop")
    public ResponseEntity<?> getMyShop() {
        User userEntity  =  userService.findById(SecurityContextHolder.getContext().getAuthentication().getName());
        if(userEntity == null)return new ResponseEntity<>("로그인 되어있지 않습니다.", httpHeaders, HttpStatus.BAD_REQUEST);
        System.out.println("접속 유저 ID : " + userEntity.getId());
        Object result = shopService.haveShop(userEntity.getId());
        if(result == null)return new ResponseEntity<>("매장 등록이 되어있지 않습니다.", httpHeaders, HttpStatus.BAD_REQUEST);
        if (result.getClass() == Shop.class) return new ResponseEntity<>(result, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
