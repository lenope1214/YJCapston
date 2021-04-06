package com.jumanji.capston.controller.exception.UserException;

import com.jumanji.capston.controller.exception.BasicException;

public class UnAuthorizedException extends BasicException {
    public UnAuthorizedException(String code, String message) {
        super(code, message);
    }
}
