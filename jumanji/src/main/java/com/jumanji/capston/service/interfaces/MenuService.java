package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.Menu;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface MenuService {
    public ResponseEntity<?> get(String menuId);
    public ResponseEntity<?> getList(String shopId);
    public ResponseEntity<?> post(Menu.Request request);
    public ResponseEntity<?> patch(Menu.Request request);
    public ResponseEntity<?> delete(String authorization, String menuId);
}
