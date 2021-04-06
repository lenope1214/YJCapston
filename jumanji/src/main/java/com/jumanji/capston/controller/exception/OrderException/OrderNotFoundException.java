package com.jumanji.capston.controller.exception.OrderException;

import com.jumanji.capston.controller.exception.BasicException;

public class OrderNotFoundException extends BasicException {
    public OrderNotFoundException(String code, String message) {
        super(code, message);
    }
}