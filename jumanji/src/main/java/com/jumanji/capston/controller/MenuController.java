package com.jumanji.capston.controller;

import com.jumanji.capston.data.Menu;
import com.jumanji.capston.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/menuList/{shopId}")
    public ResponseEntity<?> selectMenuList(@PathVariable String shopId){
        return new ResponseEntity<>(menuService.findByshopId(shopId), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/menu/{menuId}")
    public ResponseEntity<?> selectMenuById(@PathVariable String menuId){
        if(menuService.findById(menuId) == null)return new ResponseEntity<>("없는 메뉴 입니다.", httpHeaders, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(menuService.findById(menuId), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/menu")
    public ResponseEntity<?> insertMenu(@RequestBody Menu menu){
        return null;
    }
    @Transactional
    @PutMapping("/menu")
    public ResponseEntity<?> updateMenu(@RequestBody Menu menu){
        return null;
    }
    @Transactional
    @DeleteMapping("/menu")
    public ResponseEntity<?> deleteMenu(@RequestBody Menu menu){
        return null;
    }
}
