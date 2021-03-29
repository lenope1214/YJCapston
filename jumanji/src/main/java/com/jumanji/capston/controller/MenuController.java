package com.jumanji.capston.controller;

import com.jumanji.capston.data.Menu;
import com.jumanji.capston.data.Request.MenuRequest;
import com.jumanji.capston.data.Request.ShopRequest;
import com.jumanji.capston.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class MenuController {

    @Autowired
    MenuService menuService;

    @Autowired
    HttpHeaders httpHeaders;

//    @Transactional(readOnly = true)
//    @GetMapping("/menu")
//    public ResponseEntity<?> selectMenu(){
//        return null;
//    }

    @Transactional(readOnly = true)
    @GetMapping("/menuList")
    public ResponseEntity<?> selectMenuList(@RequestBody ShopRequest shopRequest){
        if(menuService.findByshopId(shopRequest.getShopId()).isEmpty())
            return new ResponseEntity<>("없는 식당번호", httpHeaders, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(menuService.findByshopId(shopRequest.getShopId()), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/menu")
    public ResponseEntity<?> selectMenuById(@RequestBody MenuRequest menuRequest){
        if(menuService.findById(menuRequest.getMenuId()) == null)
            return new ResponseEntity<>("없는 메뉴번호 입니다.", httpHeaders, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(menuService.findById(menuRequest.getMenuId()), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/menu")
    public ResponseEntity<?> insertMenu(@RequestBody MenuRequest menuRequest){
        Menu menu;
        BigDecimal menuSeq = menuService.getMenuSeqNextVal();
//        System.out.println("seq : " + menuSeq);
        menu = Menu.createMenu()
                .id(menuRequest.getShopId()+""+ menuSeq)
                .name(menuRequest.getName())
                .intro(menuRequest.getIntro())
                .price(menuRequest.getPrice())
                .duration(menuRequest.getDuration())
                .build();
        Object result = menuService.insert(menu);
        if(result.getClass() != Menu.class)
            return new ResponseEntity<>("저장 실패", httpHeaders, HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/menu")
    public ResponseEntity<?> updateMenu(@RequestBody MenuRequest menuRequest){

        return null;
    }
    @Transactional
    @DeleteMapping("/menu")
    public ResponseEntity<?> deleteMenu(@RequestBody Menu menu){
        return null;
    }
}
