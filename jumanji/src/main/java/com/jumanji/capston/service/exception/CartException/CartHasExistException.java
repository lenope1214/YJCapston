package com.jumanji.capston.service.exception.CartException;

import com.jumanji.capston.service.exception.BasicException;

public class CartHasExistException extends BasicException {
    public CartHasExistException(){
        super("error-not-defined", "you have to request again !!");
    }
    public CartHasExistException(String id){
        super("error-not-defined", "you have to request again !! 요청시간 : " + id);
    }
    public CartHasExistException(String code, String message) {
        super(code, message);
    }
}
