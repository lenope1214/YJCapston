package com.jumanji.capston.controller;

import com.jumanji.capston.controller.exception.ApiErrorResponse;
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

import java.util.List;

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
    @GetMapping("/shop/{shopId}/menuList")
    public ResponseEntity<?> selectMenuList(@PathVariable String shopId){
        System.out.println("menuList >> shopId : " + shopId);
        List<Menu> menuList;
        menuList = menuService.findContainsId(shopId);
        System.out.println("menuList info");
        System.out.println(menuList.size());
        if(menuList == null)
            return new ResponseEntity<>(new ApiErrorResponse("error-1001", "없는 식당번호"), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(menuList, HttpStatus.OK);
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
        System.out.println("메뉴 추가");
//        BigDecimal menuSeq = menuService.getMenuSeqNextVal();
//        System.out.println("seq : " + menuSeq);
        int menuQty = menuService.count(menuRequest.getShopId());
        if(menuQty > 99)return new ResponseEntity<>(new ApiErrorResponse("error-1011", "메뉴를 100개이상 등록. 삭제바람"), HttpStatus.BAD_REQUEST);
            String menuSeq = String.format("%02d", menuQty+1);
        System.out.println("menuSeq : " + menuSeq);
        menu = Menu.info()
                .id(menuRequest.getShopId()+ "" + menuSeq)
                .name(menuRequest.getName())
                .intro(menuRequest.getIntro())
                .price(menuRequest.getPrice())
                .duration(menuRequest.getDuration())
                .build();
//        System.out.println("ㅁㄴㅇㄹ");
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
    public ResponseEntity<?> deleteMenu(@RequestBody Menu.Request request){
        Menu menu = menuService.findById(request.getMenuId());
        if(menu == null)return new ResponseEntity<>(new ApiErrorResponse("error-2001", "Not Found by menu id"), HttpStatus.NOT_FOUND);
        menuService.delete(menu);
        return new ResponseEntity<>("삭제성공", httpHeaders, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/menuListAll")
    public ResponseEntity<?> menuListAll(){
        return new ResponseEntity<>(menuService.findAll(), HttpStatus.OK);
    }

}