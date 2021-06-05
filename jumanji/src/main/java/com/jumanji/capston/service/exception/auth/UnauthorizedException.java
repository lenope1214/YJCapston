package com.jumanji.capston.service.exception.auth;

import com.jumanji.capston.service.exception.BasicException;


// unauthorized = 무단 => 'jwt' 없음
// unauthenticated = 인증되지 않음 => 'jwt'는 있지만 권한 불충분.
public class UnauthorizedException extends BasicException {
    public UnauthorizedException(){
        super("error-0000", "인증되지 않음");
    }
    public UnauthorizedException(String code, String message) {
        super(code, message);
    }
}
