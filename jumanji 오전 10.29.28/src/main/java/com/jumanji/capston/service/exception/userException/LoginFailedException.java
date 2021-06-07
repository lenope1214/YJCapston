package com.jumanji.capston.service.exception.userException;

import com.jumanji.capston.service.exception.BasicException;

public class LoginFailedException extends BasicException {
    public LoginFailedException(){
        super("error-102", "login failed");
    }

//    public LoginFailedMatchException(String password) {
//        super("error-102", "login failed : " id);
//    }
}