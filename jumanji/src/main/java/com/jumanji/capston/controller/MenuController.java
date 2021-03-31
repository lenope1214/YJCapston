package com.jumanji.capston.controller;

import com.jumanji.capston.data.Menu;
import com.jumanji.capston.data.Request.MenuRequest;
import com.jumanji.capston.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @GetMapping("/menuList/{shopId}")
    public ResponseEntity<?> selectMenuList(@PathVariable String shopId){
        if(menuService.findByshopId(shopId).isEmpty())
            return new ResponseEntity<>("없는 식당번호", httpHeaders, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(menuService.findByshopId(shopId), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/menu/{menuId}")
    public ResponseEntity<?> selectMenuById(@PathVariable String menuId){
        if(menuService.findById(menuId) == null)
            return new ResponseEntity<>("없는 메뉴번호 입니다.", httpHeaders, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(menuService.findById(menuId), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/menu")
    public ResponseEntity<?> insertMenu( MenuRequest menuRequest){
        Menu menu;
        BigDecimal menuSeq = menuService.getMenuSeqNextVal();
//        System.out.println("seq : " + menuSeq);
        menu = Menu.info()
                .id(menuRequest.getShopId()+""+ menuSeq)
                .name(menuRequest.getName())
                .intro(menuRequest.getIntro())
                .price(menuRequest.getPrice())
                .duration(menuRequest.getDuration())
                .build();
        Object result =menuService.save(menu);
        if(result.getClass() != Menu.class)
            return new ResponseEntity<>("저장 실패", httpHeaders, HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Transactional
    @PatchMapping("/menu")
    public ResponseEntity<?> updateMenu(@RequestBody MenuRequest menuRequest){
        System.out.println("메뉴 수정>>> ");
        Menu menu = menuService.findById(menuRequest.getMenuId());
        if(menu == null)return new ResponseEntity<>("메뉴 ID가 없습니다.", httpHeaders, HttpStatus.BAD_REQUEST);
        System.out.println("menuId : " + menu.getId());

        menu = Menu.info()
                .id(menuRequest.getMenuId())
                .name(menuRequest.getName())
                .intro(menuRequest.getIntro())
                .price(menuRequest.getPrice())
                .duration(menuRequest.getDuration())
                .imgUrl("")
                .build();
       menuService.save(menu);
        System.out.println("menuId : " + menu.getId());
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }
    @Transactional
    @DeleteMapping("/menu")
    public ResponseEntity<?> deleteMenu(@RequestBody Menu menu){
        return new ResponseEntity<>("미구현입니다.", httpHeaders, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/menuListAll")
    public ResponseEntity<?> menuListAll(){
        return new ResponseEntity<>(menuService.findAll(), HttpStatus.OK);
    }

}