package com.jumanji.capston.service;

import com.jumanji.capston.data.Menu;
import com.jumanji.capston.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    MenuRepository menuRepository;

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
}
