package com.jumanji.capston.controller;

import com.jumanji.capston.controller.exception.ApiErrorResponse;
import com.jumanji.capston.data.Menu;
import com.jumanji.capston.service.MenuService;
import com.jumanji.capston.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class MenuController {

    @Autowired
    MenuService menuService;

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
        System.out.println("메뉴 Id : " + menuId);
        Menu menu = menuService.findById(menuId);
        if (menu == null)
            return new ResponseEntity<>("없는 메뉴번호 입니다.", httpHeaders, HttpStatus.BAD_REQUEST);
        Menu.Response response = new Menu.Response();
        response.parse(menu);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Transactional(readOnly = true)
    @GetMapping("/menuList/{shopId}")
    public ResponseEntity<?> selectMenuList(@PathVariable String shopId) {
        System.out.println("menuList >> shopId : " + shopId);
        List<Menu> menuList;
        menuList = menuService.findContainsId(shopId);
        System.out.println("menuList info");
        System.out.println(menuList.size());

        return new ResponseEntity<>(menuList, HttpStatus.OK);
    }


    @Transactional
    @PostMapping("/menu") // post
    public ResponseEntity<?> insertMenu(Menu.info request) {
        Menu menu;
        System.out.println("메뉴 추가");
        String menuId = request.getShopId() + request.getName();
        String path = "shop/" + request.getShopId() + "/menu/";
        String imgPath = null;
        if (request.getImg() != null)
            imgPath = storageService.store(request.getImg(), request.getName(), path.split("/"));
        menu = Menu.init()
                .id(menuId)
                .name(request.getName())
                .intro(request.getIntro())
                .price(request.getPrice())
                .duration(request.getDuration())
                .imgPath(imgPath)
                .build();
//        System.out.println("ㅁㄴㅇㄹ");
        Menu result = menuService.save(menu);
//        Menu result = menu;
        Menu.Response response = new Menu.Response();
        response.parse(result);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Transactional
    @PatchMapping("/menu") // patch
    public ResponseEntity<?> updateMenu(@RequestBody Menu.Request request) {
        System.out.println("메뉴 수정>>> ");

        // 권한확인 해야함. 로그인유저 의 매장인지.
//        String menuId = request.getMenuId(request);
        Menu menu = menuService.findById(request.getMenuId());
        if (menu == null) return new ResponseEntity<>("메뉴 ID가 없습니다.", httpHeaders, HttpStatus.BAD_REQUEST);
        System.out.println("menuId : " + menu.getId());

        menu.update(request);

        menuService.save(menu);
        System.out.println("menuId : " + menu.getId());
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @Transactional
//    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    @DeleteMapping("/menu") // Delete
    public ResponseEntity<?> deleteMenu(@RequestBody Menu.Request request) {
//        String menuId = request.getMenuId(request);
        // 추후에 유저 확인..
        System.out.println("삭제 메뉴 id " + request.getMenuId());
        Menu menu = menuService.findById(request.getMenuId());
        if (menu == null)
            return new ResponseEntity<>(new ApiErrorResponse("error-2001", "Not Found by menu id"), HttpStatus.NOT_FOUND);
        menuService.delete(menu);
        return new ResponseEntity<>("삭제성공", httpHeaders, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/menuListAll")
    public ResponseEntity<?> menuListAll() {
        return new ResponseEntity<>(menuService.findAll(), HttpStatus.OK);
    }

}