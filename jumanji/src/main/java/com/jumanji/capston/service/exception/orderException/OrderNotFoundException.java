package com.jumanji.capston.service.exception.orderException;

import com.jumanji.capston.service.exception.BasicException;

public class OrderNotFoundException extends BasicException {
    public OrderNotFoundException(){
        super("error-501", "주문을 찾을 수 없습니다.");
    }
    public OrderNotFoundException(String id){
        super("error-501", "주문을 찾을 수 없습니다. id : " + id);
    }
    public OrderNotFoundException(String code, String message) {
        super(code, message);
    }
}
