package com.jumanji.capston.controller.exception.MenuException;

import com.jumanji.capston.controller.exception.BasicException;

public class MenuNotFoundException extends BasicException {
    public MenuNotFoundException() {
        super("2001", "없는 메뉴 입니다.");
    }
    public MenuNotFoundException(String code, String message) {
        super(code, message);
    }
}