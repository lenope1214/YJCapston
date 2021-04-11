package com.jumanji.capston.service.exception.UserException;

import com.jumanji.capston.service.exception.BasicException;
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