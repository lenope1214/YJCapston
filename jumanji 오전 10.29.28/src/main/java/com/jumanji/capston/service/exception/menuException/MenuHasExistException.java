package com.jumanji.capston.service.exception.menuException;

import com.jumanji.capston.service.exception.BasicException;

public class MenuHasExistException extends BasicException {
    public MenuHasExistException(){
        super("error-302", "메뉴정보가 이미 있습니다.");
    }
    public MenuHasExistException(String id){
        super("error-302", "메뉴정보가 이미 있습니다. id : " + id);
    }
    public MenuHasExistException(String code, String message) {
        super(code, message);
    }
}