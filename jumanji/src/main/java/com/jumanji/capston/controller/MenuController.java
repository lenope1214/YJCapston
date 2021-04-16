package com.jumanji.capston.controller;

import com.jumanji.capston.data.Menu;
import com.jumanji.capston.service.MenuServiceImpl;
import com.jumanji.capston.service.ShopServiceImpl;
import com.jumanji.capston.service.StorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class MenuController  {

    @Autowired
    MenuServiceImpl menuService;
    @Autowired
    ShopServiceImpl shopService;

    @Autowired
    StorageServiceImpl storageService;

    @Autowired
    HttpHeaders httpHeaders;


    //    @Transactional(readOnly = true)
//    @GetMapping("/menu")
//    public ResponseEntity<?> selectMenu(){
//        return null;
//    }
    @Transactional(readOnly = true)
    @GetMapping("/menu/{menuId}")
    public ResponseEntity<?> selectMenuById(@PathVariable String menuId) {
//        String menuId = request.getShopId() + request.getName();
        return menuService.get(menuId);
    }

    @Transactional(readOnly = true)
    @GetMapping("/menuList")
    public ResponseEntity<?> AllMenu(@RequestHeader String authorization){
        return menuService.findAll(authorization);

    }


    @Transactional(readOnly = true)
    @GetMapping("/menuList/{shopId}")
    public ResponseEntity<?> selectMenuList(@PathVariable String shopId) {
        return menuService.getList(shopId);
    }


    @Transactional
    @PostMapping("/menu") // post
    public ResponseEntity<?> postMenu(Menu.Request request) {
        return menuService.post(request);
    }

    @Transactional
    @PatchMapping("/menu") // patch
    public ResponseEntity<?> patchMenu(@RequestBody Menu.Request request) {
        return menuService.patch(request);
    }

    @Transactional
    @DeleteMapping("/menu/{menuId}") // Delete
    public ResponseEntity<?> deleteMenu(@RequestHeader String authorization, @PathVariable String menuId) throws AuthenticationException {
        System.out.println("메뉴 삭제 요청");
        return menuService.delete(authorization, menuId);
    }

    @Transactional
    @PatchMapping("/menu/{menuId}/popular")
    public ResponseEntity<?> updateShopIsOpen(@RequestHeader String authorization, @PathVariable String menuId) {
        return menuService.patchStatus(authorization, menuId, "popular");
    }


    //
    @Transactional
    @PatchMapping("/menu/{menuId}/sale")
    public ResponseEntity<?> updateShopIsRsPos(@RequestHeader String authorization, @PathVariable String menuId) {
        return menuService.patchStatus(authorization, menuId, "sale");
    }
}