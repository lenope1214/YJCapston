package com.jumanji.capston.service.exception.markException;

import com.jumanji.capston.service.exception.BasicException;

public class MarkHasExistException extends BasicException {
    public MarkHasExistException(){
        super("error-1102", "찜이 이미 등록되어 있습니다.");
    }
    public MarkHasExistException(String shopId){
        super("error-1102", "찜이 이미 등록되어 있습니다." + shopId);
    }
    public MarkHasExistException(String code, String message) {
        super(code, message);
    }
}