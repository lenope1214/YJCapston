package com.jumanji.capston.service;

import com.jumanji.capston.controller.exception.MenuException.MenuAlreadUsedException;
import com.jumanji.capston.controller.exception.MenuException.MenuNotFoundException;
import com.jumanji.capston.data.Menu;
import com.jumanji.capston.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    HttpHeaders httpHeaders;
    @Autowired
    StorageService storageService;

//    public BigDecimal getMenuSeqNextVal() {
//        return menuRepository.getMenuSeqNextVal();
//    }

    public ResponseEntity<?> findById(String menuId) {
        System.out.println("메뉴 Id : " + menuId);

        Menu menu = menuRepository.findById(menuId).get();
        if (menu == null)
            return new ResponseEntity<>("없는 메뉴번호 입니다.", httpHeaders, HttpStatus.BAD_REQUEST);
        Menu.Response response = new Menu.Response(menu);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    public boolean findShopIdByMenuId(String menuId){
//        return menuRepository.findShopIdByMenuId(menuId);
//    }

    public int count(String id){
        return menuRepository.countMenusByIdContains(id);
    }


    public Menu save(Menu _menu) {
        return menuRepository.save(_menu);
    }

    public void delete(String menuId) {
        menuRepository.delete( menuRepository.findById(menuId).get());
    }

    public List<Menu> findContainsId(String shopId) {
        return menuRepository.findByIdContains(shopId);
    }

    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    public ResponseEntity<?> postMenu(Menu.info request) {
        String menuId = request.getShopId() + request.getName();
        isPresent(menuId);
        Menu menu;
        System.out.println("메뉴 추가");
        String path = "";
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
        Menu result = menuRepository.save(menu);
        Menu.Response response = new Menu.Response(result);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 있는 메뉴인지 확인
    public boolean isEmpty(String id){
        if(!menuRepository.findById(id).isPresent())return true;
        return true;
    }

    public boolean isPresent(String menuId){
        if(menuRepository.findById(menuId).isPresent())return true;
        throw new MenuNotFoundException();
    }

    public ResponseEntity<?> patchMenu(Menu.Request request) {
        System.out.println("메뉴 수정>>> ");
        isPresent(request.getMenuId());
        // 권한확인 해야함. 로그인유저 의 매장인지.
//        String menuId = request.getMenuId(request);
        Menu menu = menuRepository.findById(request.getMenuId()).get();
        System.out.println("menuId : " + menu.getId());

        menu.update(request);

        menuRepository.save(menu);
        Menu.Response response = new Menu.Response(menu);
        System.out.println("menuId : " + menu.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
