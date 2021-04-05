package com.jumanji.capston.controller.exception.ShopException;

import com.jumanji.capston.controller.exception.BasicException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopNotFoundException extends BasicException {
    public ShopNotFoundException(String code, String message) {
        super(code, message);
    }
}