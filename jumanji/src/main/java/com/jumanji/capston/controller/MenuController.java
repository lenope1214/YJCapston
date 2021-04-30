package com.jumanji.capston.controller;

import com.jumanji.capston.data.Menu;
import com.jumanji.capston.service.MenuServiceImpl;
import com.jumanji.capston.service.ShopServiceImpl;
import com.jumanji.capston.service.StorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.util.ArrayList;
import java.util.List;

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
        Menu menu = menuService.get(menuId);
        Menu.Response response = new Menu.Response(menu);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Transactional(readOnly = true)
    @GetMapping("/menuList/{shopId}")
    public ResponseEntity<?> selectMenuList(@PathVariable String shopId) {
        List<Menu> menuList = menuService.getList(shopId);
        List<Menu.Response> response = new ArrayList<>();
        for(Menu menu : menuList){
            response.add(new Menu.Response(menu));
        }
        if(menuList.isEmpty())return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Transactional
    @PostMapping("/menu") // post
    public ResponseEntity<?> postMenu(@RequestHeader String authorization, Menu.Request request) {
        Menu menu = menuService.post(authorization, request);
        Menu.Response response = new Menu.Response(menu);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Transactional
    @PatchMapping("/menu") // patch
    public ResponseEntity<?> patchMenu(@RequestHeader String authorization, @RequestBody Menu.Request request) {
        Menu menu =  menuService.patch(authorization, request);
        Menu.Response response = new Menu.Response(menu);
        System.out.println("menuId : " + menu.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/menu/{menuId}") // Delete
    public ResponseEntity<?> deleteMenu(@RequestHeader String authorization, @PathVariable String menuId) throws AuthenticationException {
        System.out.println("메뉴 삭제 요청");
        menuService.delete(authorization, menuId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Transactional
    @PatchMapping("/menu/{menuId}/popular")
    public ResponseEntity<?> updateShopIsOpen(@RequestHeader String authorization, @PathVariable String menuId) {
        Menu menu = menuService.patchStatus(authorization, menuId, "popular");
        return new ResponseEntity<>(menu.getIsPopular(), HttpStatus.OK);
    }


    //
    @Transactional
    @PatchMapping("/menu/{menuId}/sale")
    public ResponseEntity<?> updateShopIsRsPos(@RequestHeader String authorization, @PathVariable String menuId) {
        Menu menu = menuService.patchStatus(authorization, menuId, "sale");
        return new ResponseEntity<>(menu.getIsSale(), HttpStatus.OK);
    }
}