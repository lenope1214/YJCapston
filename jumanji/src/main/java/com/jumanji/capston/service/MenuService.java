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

    public Menu findById(String id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id를 확인해주세요!!!"));
    }


    public Menu insert(Menu _menu) {
        return menuRepository.save(_menu);
    }

    public String delete(Menu _menu) {
        System.out.println("");
        Menu menu = menuRepository.findById(_menu.getId()).
        orElseThrow(() -> new IllegalArgumentException("id를 확인해주세요!!!"));
        menuRepository.delete(menu);
        return "ok";
    }

    public List<Menu> findByshopId(String shopId){ return menuRepository.findByIdContains(shopId);}
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }
}
