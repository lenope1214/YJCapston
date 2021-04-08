package com.jumanji.capston.controller.exception.UserException;

import com.jumanji.capston.controller.exception.BasicException;

public class UserNotFoundException extends BasicException {
    public UserNotFoundException(){
        super("0001", "not found user with id");
    }

    public UserNotFoundException(String id){
        super("0001", "not found user with id : " + id);
    }

    public UserNotFoundException(String code, String message) {
        super(code, message);
    }
}