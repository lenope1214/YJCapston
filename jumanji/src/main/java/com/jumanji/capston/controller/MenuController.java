package com.jumanji.capston.controller;

import com.jumanji.capston.controller.commons.Controller;
import com.jumanji.capston.controller.exception.ApiErrorResponse;
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

import java.util.List;

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
        System.out.println("메뉴 Id : " + menuId);
        Menu menu = menuService.findById(menuId);
        if (menu == null)
            return new ResponseEntity<>("없는 메뉴번호 입니다.", httpHeaders, HttpStatus.BAD_REQUEST);
        Menu.Response response = new Menu.Response(menu);

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
        if(menuService.findById(request.getShopId()+request.getName()) != null)return new ResponseEntity<>("있는 메뉴입니다.", httpHeaders, HttpStatus.LOCKED);
        Menu menu;
        System.out.println("메뉴 추가");
        String menuId = request.getShopId() + request.getName();
        String path = "shop/" + request.getShopId() + "/menu/";
        String imgPath = null;
        if (request.getImg() != null)
            imgPath = storageService.store(request.getImg(), request.getName().replace(" ", "_"), path.split("/"));
        menu = Menu.init()
                .id(menuId)
                .name(request.getName().replace(" ", "_"))
                .intro(request.getIntro())
                .price(request.getPrice())
                .duration(request.getDuration())
                .imgPath(imgPath)
                .build();
//        System.out.println("ㅁㄴㅇㄹ");
        Menu result = menuService.save(menu);
//        Menu result = menu;
        Menu.Response response = new Menu.Response(result);

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
    @DeleteMapping("/menu/{menuId}") // Delete
    public ResponseEntity<?> deleteMenu(@RequestHeader String authorization, @PathVariable String menuId) {
        String loginId = getLoginUserId(authorization);
        if(loginId.equals(shopService.findById(menuId.substring(0, 10)).getOwner().getId())) {
            menuService.delete(menuId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else return new ResponseEntity<>(new ApiErrorResponse("error-0000", "권한이 없습니다."), HttpStatus.FORBIDDEN);
    }

    @Transactional(readOnly = true)
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/menuListAll")
    public ResponseEntity<?> menuListAll() {
        return new ResponseEntity<>(menuService.findAll(), HttpStatus.OK);
    }

}