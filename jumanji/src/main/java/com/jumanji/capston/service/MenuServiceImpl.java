package com.jumanji.capston.service;

import com.jumanji.capston.data.DateOperator;
import com.jumanji.capston.data.Menu;
import com.jumanji.capston.data.Shop;
import com.jumanji.capston.repository.MenuRepository;
import com.jumanji.capston.service.exception.menuException.MenuHasExistException;
import com.jumanji.capston.service.exception.menuException.MenuNotFoundException;
import com.jumanji.capston.service.interfaces.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements BasicService<Menu, Menu.Request> {
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

    public int count(String id) {
        return menuRepository.countMenusByIdContains(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Menu get(@Nullable String authorization, String... str) {
        String menuId = str[0];
        Menu menu = isPresent(menuId);
        return menu;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Menu> getList(@Nullable String authorization, String... str) {
        String shopId = str[0];
        System.out.println("menuList >> shopId : " + shopId);
        shopService.isPresent(shopId);
        List<Menu> menuList;
        menuList = menuRepository.findByShopId(shopId);
        System.out.println("menuList info");
        System.out.println(menuList.size());

        return menuList;
    }

    @Override
    @Transactional
    public Menu post(String authorization, Menu.Request request) {
        Menu menu;
        Shop shop;
        String menuId = request.getShopId().substring(0, 2) + 'm' + DateOperator.dateToYYYYMMDDHHMMSS(new Date());
        System.out.println("menuId : " + menuId);


        isEmpty(menuId);
        shop = shopService.isPresent(request.getShopId());
        System.out.println("메뉴 추가");
        String path = "shop/" + request.getShopId() + "/" + "menu";
        String imgPath = null;
        if (request.getImg() != null)
            imgPath = storageService.store(request.getImg(), request.getName().replace(" ", "_"), path.split("/"));
//        System.out.println("메뉴 이미지 path : " + imgPath);
//        System.out.println("메뉴명 : " + request.getName());
//        System.out.println("넣는 메뉴명 : " + request.getName().replace(" ", "_"));
//        System.out.println("넣는 메뉴 id : " + menuId);
        menu = Menu.init()
                .id(menuId)
                .name(request.getName())
                .intro(request.getIntro())
                .price(request.getPrice())
                .duration(request.getDuration())
                .imgPath(imgPath)
                .shop(shop)
                .build();
//        System.out.println("save 전 menu Id  : " + menu.getId());
        return menuRepository.saveAndFlush(menu);
    }

    public Menu patchStatus(String authorization, String menuId, String target) {
        String loginId = userService.getMyId(authorization);
        shopService.isOwnShop(loginId, menuId.substring(0, 10));
        shopService.isPresent(menuId.substring(0, 10));
        Menu menu = menuService.isPresent(menuId);
        menu.reverseStatus(target);
        return menuRepository.save(menu);
    }

    @Override
    public Menu patch(String authorization, Menu.Request request) {
        return null;
    }

    @Override
    public void delete(@Nullable String authorization, String... str){
        String shopId = str[0];
        String menuId = str[1];
        String loginId = userService.getMyId(authorization);

        // 유효성 검사
        userService.isPresent(loginId); // 존재하는 유저인지
//        shopService.isPresent(shopId); // 존재하는 식당인지
        shopService.isOwnShop(loginId, shopId); // 내 식당인지 체크
        Menu menu = isPresent(menuId);
        menuRepository.delete(menu);
    }

    @Override
    public Menu isPresent(String id) {
        Optional<Menu> menu = menuRepository.findById(id);
        if(menu.isPresent())return menu.get();
        throw new MenuNotFoundException();
    }

    @Override
    public boolean isEmpty(String id) {
        Optional<Menu> menu = menuRepository.findById(id);
        if(menu.isEmpty())return true;
        throw new MenuHasExistException();
    }
//    @Override
//    @Transactional

}