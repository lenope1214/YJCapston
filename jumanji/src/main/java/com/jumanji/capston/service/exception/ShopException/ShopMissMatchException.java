package com.jumanji.capston.service.exception.ShopException;

import com.jumanji.capston.service.exception.BasicException;

public class ShopMissMatchException extends BasicException {
    public ShopMissMatchException() {
        super("error-1003", "잘못된 식당으로 요청이 왔습니다.");
    }
    public ShopMissMatchException(String code, String message) {
        super(code, message);
    }
}
