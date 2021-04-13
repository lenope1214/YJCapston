package com.jumanji.capston.service.exception.UserException;

import com.jumanji.capston.service.exception.BasicException;

public class UserHasExistException extends BasicException {
    public UserHasExistException() {
        super("error-0003", "the ID in use");
    }

    public UserHasExistException(String id) {
        super("0003", "these id has used - id : " + id);
    }

    public UserHasExistException(String code, String message) {
        super(code, message);
    }


}