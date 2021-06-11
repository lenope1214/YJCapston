package com.jumanji.capston.service.exception.userException;

import com.jumanji.capston.service.exception.BasicException;

public class UserHasExistException extends BasicException {
    public UserHasExistException() {
        super("error-103", "The id has exist");
    }

    public UserHasExistException(String id) {
        super("error-103", "The id has exist - id : " + id);
    }

    public UserHasExistException(String code, String message) {
        super(code, message);
    }


}