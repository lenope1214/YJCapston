package com.jumanji.capston.service.exception.orderMenuException;

import com.jumanji.capston.service.exception.BasicException;

public class OrderMenuNotFoundException extends BasicException {
    public OrderMenuNotFoundException(){
        super("error-4001", "없는 주문번호 입니다.");
    }
    public OrderMenuNotFoundException(String code, String message) {
        super(code, message);
    }
}