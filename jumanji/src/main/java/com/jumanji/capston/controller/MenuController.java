package com.jumanji.capston.controller;

import com.jumanji.capston.controller.commons.Controller;
import com.jumanji.capston.data.Menu;
import com.jumanji.capston.service.MenuService;
import com.jumanji.capston.service.ShopService;
import com.jumanji.capston.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class MenuController extends Controller {

    @Autowired
    MenuService menuService;
    @Autowired
    ShopService shopService;

    @Autowired
    StorageService storageService;

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
        return menuService.findById(menuId);
    }


    @Transactional(readOnly = true)
    @GetMapping("/menuList/{shopId}")
    public ResponseEntity<?> selectMenuList(@PathVariable String shopId) {
        return menuService.getMenuListByShopId(shopId);
    }


    @Transactional
    @PostMapping("/menu") // post
    public ResponseEntity<?> insertMenu(Menu.info request) {
        return menuService.postMenu(request);
    }

    @Transactional
    @PatchMapping("/menu") // patch
    public ResponseEntity<?> patchMenu(@RequestBody Menu.Request request) {
        return menuService.patchMenu(request);
    }

    @Transactional
    @DeleteMapping("/menu/{menuId}") // Delete
    public ResponseEntity<?> deleteMenu(@RequestHeader String authorization, @PathVariable String menuId) throws AuthenticationException {
        return menuService.delete(authorization, menuId);
    }

    @Transactional(readOnly = true)
    @GetMapping("/menuListAll")
    public ResponseEntity<?> menuListAll() {
        return new ResponseEntity<>(menuService.findAll(), HttpStatus.OK);
    }

}