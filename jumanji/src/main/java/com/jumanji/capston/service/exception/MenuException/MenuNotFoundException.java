package com.jumanji.capston.service.exception.menuException;

import com.jumanji.capston.service.exception.BasicException;

public class MenuNotFoundException extends BasicException {
    public MenuNotFoundException() {
        super("2001", "없는 메뉴 입니다.");
    }
    public MenuNotFoundException(String code, String message) {
        super(code, message);
    }
}