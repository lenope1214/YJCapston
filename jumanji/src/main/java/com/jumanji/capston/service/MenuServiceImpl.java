package com.jumanji.capston.service;

import com.jumanji.capston.data.Menu;
import com.jumanji.capston.data.User;
import com.jumanji.capston.repository.MenuRepository;
import com.jumanji.capston.service.exception.Auth.ForbiddenException;
import com.jumanji.capston.service.exception.MenuException.MenuHasExistException;
import com.jumanji.capston.service.exception.MenuException.MenuNotFoundException;
import com.jumanji.capston.service.interfaces.BasicService;
import com.jumanji.capston.service.interfaces.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService, BasicService {
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    HttpHeaders httpHeaders;
    @Autowired
    StorageServiceImpl storageService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ShopServiceImpl shopService;
    @Autowired
    MenuServiceImpl menuService;
//    public BigDecimal getMenuSeqNextVal() {
//        return menuRepository.getMenuSeqNextVal();
//    }

    public Menu getMenuInfo(String menuId) {
        isPresent(menuId);
        Menu menu = menuRepository.findById(menuId).get();
        return menu;
    }

    public int count(String id){
        return menuRepository.countMenusByIdContains(id);
    }

    @Override
    public Menu get(String menuId) {
        isPresent(menuId);
        Menu menu = menuRepository.findById(menuId).get();
        return menu;
    }

    @Override
    public List<Menu> getList(String shopId) {
        System.out.println("menuList >> shopId : " + shopId);
        shopService.isPresent(shopId);
        List<Menu> menuList;
        menuList = menuRepository.findByIdContains(shopId);
        System.out.println("menuList info");
        System.out.println(menuList.size());

        return menuList;
    }

    @Override
    public Menu post(String authorization, Menu.Request request) {
        String menuId = request.getShopId() + request.getName();
        isEmpty(menuId);
        Menu menu;
        System.out.println("메뉴 추가");
        String path = "shop/" + request.getShopId() +"/" + "menu";
        String imgPath = null;
        if (request.getImg() != null)
            imgPath = storageService.store(request.getImg(), request.getName().replace(" ", "_"), path.split("/"));
        System.out.println("메뉴 이미지 path : " + imgPath);
        System.out.println("메뉴명 : " + request.getName());
        System.out.println("넣는 메뉴명 : " + request.getName().replace(" ", "_"));
        System.out.println("넣는 메뉴 id : " + menuId);
        menu = Menu.init()
                .id(menuId)
                .intro(request.getIntro())
                .price(request.getPrice())
                .duration(request.getDuration())
                .imgPath(imgPath)
                .build();
        System.out.println("save 전 menu Id  : " + menu.getId());
        return menuRepository.save(menu);
    }

    @Override
    public Menu patch(String authorization, Menu.Request request) {
        System.out.println("메뉴 수정>>> ");
        isPresent(request.getMenuId());
        // 권한확인 해야함. 로그인유저 의 매장인지.
//        String menuId = request.getMenuId(request);
        Menu menu = menuRepository.findById(request.getMenuId()).get();
        System.out.println("menuId : " + menu.getId());

        menu.update(request);

        return menuRepository.save(menu);
    }

    public void delete(String authorization, String menuId) {
        String loginId = userService.getMyId(authorization);
        User loginUser = userService.get(loginId);
        String shopId = menuId.substring(0, 10);

        // 유효성 검사
        userService.isPresent(loginId); // 존재하는 유저인지
        shopService.isPresent(shopId); // 존재하는 식당인지
        shopService.isOwnShop(loginId, shopId); // 내 식당인지 체크

        menuRepository.delete( menuRepository.findById(menuId).get());
    }

    public Menu patchStatus(String authorization, String menuId, String target) {
        String loginId = userService.getMyId(authorization);
        shopService.isOwnShop(loginId, menuId.substring(0,10));
        shopService.isPresent(menuId.substring(0,10));
        menuService.isPresent(menuId);
        Menu menu = menuService.getMenuInfo(menuId);
        menu.reverseStatus(target);
        return menuRepository.save(menu);
    }

    // 있는 메뉴인지 확인
    public boolean isEmpty(String id){
        if(menuRepository.findById(id).isEmpty())return true;
        throw new MenuHasExistException();
    }

    public boolean isPresent(String menuId){
        if(menuRepository.findById(menuId).isPresent())return true;
        throw new MenuNotFoundException();
    }

}