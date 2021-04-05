package com.jumanji.capston.controller.exception.UserException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordMissMatchException extends RuntimeException {
    private String password;

    public PasswordMissMatchException(String password) {
        this.password = password;
    }
}