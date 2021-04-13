package com.jumanji.capston.service.exception.UserException;

import com.jumanji.capston.service.exception.BasicException;

public class PasswordMissMatchException extends BasicException {
    public PasswordMissMatchException(){
        super("error-not defined", "miss match password");
    }

    public PasswordMissMatchException(String password) {
        super("error-", "miss match password : " + password);
    }
}