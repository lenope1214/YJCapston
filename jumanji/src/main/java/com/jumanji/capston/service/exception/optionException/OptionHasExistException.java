package com.jumanji.capston.service.exception.optionException;

import com.jumanji.capston.service.exception.BasicException;

public class OptionHasExistException extends BasicException {
    public OptionHasExistException(){
        super("2002", "존재하는 옵션 그룹입니다.");
    }
    public OptionHasExistException(String id){
        super("2002", "존재하는 옵션 그룹입니다. : " + id);
    }
    public OptionHasExistException(String code, String message) {
        super(code, message);
    }
}