package com.jumanji.capston.service.exception.CartException;

import com.jumanji.capston.service.exception.BasicException;

public class CartNotFoundException extends BasicException {
    public CartNotFoundException(){
        super("error-not-defined", "Cart Not Found !!");
    }
    public CartNotFoundException(String id){
        super("error-not-defined", "Cart Not Found with id : " + id);
    }
    public CartNotFoundException(String code, String message) {
        super(code, message);
    }
}
