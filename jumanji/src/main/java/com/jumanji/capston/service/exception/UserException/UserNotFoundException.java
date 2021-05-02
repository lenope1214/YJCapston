package com.jumanji.capston.service.exception.UserException;

import com.jumanji.capston.service.exception.BasicException;

public class UserNotFoundException extends BasicException {
    public UserNotFoundException(){
        super("error-101", "Not Found User with id");
    }

    public UserNotFoundException(String id){
        super("error-101", "Not Found User with id : " + id);
    }

    public UserNotFoundException(String code, String message) {
        super(code, message);
    }


}