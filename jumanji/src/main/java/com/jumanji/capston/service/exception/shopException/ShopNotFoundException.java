package com.jumanji.capston.service.exception.shopException;

import com.jumanji.capston.service.exception.BasicException;
import lombok.Getter;
import lombok.Setter;

public class ShopNotFoundException extends BasicException {
    public ShopNotFoundException() {
        super("error-201", "없는 식당번호 입니다.");
    }
    public ShopNotFoundException(String code, String message) {
        super(code, message);
    }
}