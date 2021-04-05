package com.jumanji.capston.controller.exception.ShopException;

import com.jumanji.capston.controller.exception.BasicException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopHasExistException extends BasicException {

    public ShopHasExistException(){
        super("error-1002", "이미 등록된 사업자 번호입니다.");
    }
    public ShopHasExistException(String code, String message) {
        super(code, message);
    }
}
