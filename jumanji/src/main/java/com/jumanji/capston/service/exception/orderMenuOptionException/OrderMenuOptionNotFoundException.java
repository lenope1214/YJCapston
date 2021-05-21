package com.jumanji.capston.service.exception.orderMenuOptionException;

import com.jumanji.capston.service.exception.BasicException;

public class OrderMenuOptionNotFoundException extends BasicException {
    public OrderMenuOptionNotFoundException() {
        super("2001", "없는 옵션 그룹 입니다.");
    }
    public OrderMenuOptionNotFoundException(String code, String message) {
        super(code, message);
    }
}