package com.jumanji.capston.service.exception.orderException;

import com.jumanji.capston.service.exception.BasicException;

public class OrderNotFoundException extends BasicException {
    public OrderNotFoundException(){
        super("error-not-defined", "Order Not Found !!");
    }
    public OrderNotFoundException(String id){
        super("error-not-defined", "Order Not Found with id : " + id);
    }
    public OrderNotFoundException(String code, String message) {
        super(code, message);
    }
}