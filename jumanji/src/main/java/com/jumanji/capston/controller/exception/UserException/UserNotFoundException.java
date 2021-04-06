package com.jumanji.capston.controller.exception.UserException;

import com.jumanji.capston.controller.exception.BasicException;

public class UserNotFoundException extends BasicException {
    public UserNotFoundException(String code, String message) {
        super(code, message);
    }
}