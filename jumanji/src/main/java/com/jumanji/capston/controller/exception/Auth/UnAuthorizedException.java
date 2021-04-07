package com.jumanji.capston.controller.exception.Auth;

import com.jumanji.capston.controller.exception.BasicException;


// unauthorized = 무단
// unauthenticated = 인증되지 않음
public class UnAuthorizedException extends BasicException {
    public UnAuthorizedException(){
        super("0000", "권한없음");
    }
    public UnAuthorizedException(String code, String message) {
        super(code, message);
    }
}
