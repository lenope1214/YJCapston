package com.jumanji.capston.service.exception.menuException;

import com.jumanji.capston.service.exception.BasicException;

public class MenuNotFoundException extends BasicException {
    public MenuNotFoundException() {
        super("error-301", "없는 메뉴 입니다.");
    }
    public MenuNotFoundException(String name) {
        super("error-301", "없는 메뉴 입니다. 메뉴 이름 : " + name);
    }
    public MenuNotFoundException(String code, String message) {
        super(code, message);
    }
}