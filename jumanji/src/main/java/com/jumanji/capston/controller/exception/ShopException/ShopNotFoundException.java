package com.jumanji.capston.controller.exception.ShopException;

import com.jumanji.capston.controller.exception.BasicException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopNotFoundException extends BasicException {
    public ShopNotFoundException() {
        super("1001", "없는 매장번호 입니다.");
    }
    public ShopNotFoundException(String code, String message) {
        super(code, message);
    }
}