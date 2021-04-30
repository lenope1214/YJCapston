package com.jumanji.capston.service.exception.MenuException;

import com.jumanji.capston.service.exception.BasicException;

public class MenuHasExistException extends BasicException {
    public MenuHasExistException(){
        super("2002", "menu id being used");
    }
    public MenuHasExistException(String id){
        super("2002", "menu is already used id : " + id);
    }
    public MenuHasExistException(String code, String message) {
        super(code, message);
    }
}