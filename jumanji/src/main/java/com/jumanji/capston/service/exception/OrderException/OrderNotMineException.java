package com.jumanji.capston.service.exception.OrderException;

import com.jumanji.capston.service.exception.BasicException;

public class OrderNotMineException extends BasicException {
    public OrderNotMineException(){
        super("error-504", "내 주문이 아닙니다.");
    }
    public OrderNotMineException(String code, String message) {
        super(code, message);
    }
}