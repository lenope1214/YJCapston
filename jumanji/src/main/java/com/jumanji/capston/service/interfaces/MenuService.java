package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.Menu;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MenuService {
    public Menu get(String menuId);
    public List<Menu> getList(String shopId);
    public Menu post(String authorization, Menu.Request request);
    public Menu patch(String authorization, Menu.Request request);
    public void delete(String authorization, String menuId);
}
