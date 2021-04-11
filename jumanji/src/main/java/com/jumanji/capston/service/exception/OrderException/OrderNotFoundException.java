package com.jumanji.capston.service.exception.OrderException;

import com.jumanji.capston.service.exception.BasicException;

public class OrderNotFoundException extends BasicException {
    public OrderNotFoundException(){
        super("error-3001", "없는 주문번호 입니다.");
    }
    public OrderNotFoundException(String code, String message) {
        super(code, message);
    }
}