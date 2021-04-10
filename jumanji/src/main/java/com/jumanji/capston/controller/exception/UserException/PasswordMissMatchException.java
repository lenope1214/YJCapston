package com.jumanji.capston.controller.exception.UserException;

import com.jumanji.capston.controller.exception.BasicException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordMissMatchException extends BasicException {
    private String password;
    public PasswordMissMatchException(){
        super("error-", "miss match password");
    }

    public PasswordMissMatchException(String password) {
        super("error-", "miss match password : " + password);
    }
}