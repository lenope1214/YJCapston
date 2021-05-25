package com.jumanji.capston.service.exception.userException;

import com.jumanji.capston.service.exception.BasicException;

public class DoLoginExistException extends BasicException {
    public DoLoginExistException() {
        super("error-login", "You have to login");
    }
}