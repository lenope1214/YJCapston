package com.jumanji.capston.service.exception.orderException;

import com.jumanji.capston.service.exception.BasicException;

public class OrderHasExistException extends BasicException {
    public OrderHasExistException(){
        super("error-not-defined", "you have to request again !!");
    }
    public OrderHasExistException(String id){
        super("error-not-defined", "you have to request again !! 요청시간 : " + id);
    }
    public OrderHasExistException(String code, String message) {
        super(code, message);
    }
}
