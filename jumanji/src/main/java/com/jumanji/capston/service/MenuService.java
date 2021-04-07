package com.jumanji.capston.service;

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

    public Menu findById(String menuId) {
        if(menuRepository.findById(menuId).isPresent()) return menuRepository.findById(menuId).get();
        else return null;
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

    public void delete(String _menu) {
        menuRepository.delete( findById(_menu));
    }

    public List<Menu> findContainsId(String shopId) {
        return menuRepository.findByIdContains(shopId);
    }

    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    public void postMenu(Menu.info request) {
        if (menuService.findById(request.getShopId() + request.getName()) != null)
            return new ResponseEntity<>("있는 메뉴입니다.", httpHeaders, HttpStatus.LOCKED);
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

    // 있는 메뉴인지 확인
    public boolean isPresent(String id){
        if(menuRepository.findById(id).isPresent())throw Menu
        return false;
    }
}
