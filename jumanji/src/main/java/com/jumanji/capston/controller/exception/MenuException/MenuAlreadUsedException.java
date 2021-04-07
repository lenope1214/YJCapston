package com.jumanji.capston.controller.exception.MenuException;

import com.jumanji.capston.controller.exception.BasicException;

public class MenuAlreadUsedException extends BasicException {
    public MenuAlreadUsedException(){
        super("2002", "menu is already used");
    }
    public MenuAlreadUsedException(String id){
        super("2002", "menu is already used id : " + id);
    }
    public MenuAlreadUsedException(String code, String message) {
        super(code, message);
    }
}