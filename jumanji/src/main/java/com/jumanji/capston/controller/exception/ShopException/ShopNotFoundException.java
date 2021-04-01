package com.jumanji.capston.controller.exception.ShopException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopNotFoundException  extends RuntimeException {
    private String id;
    private String code;

    public ShopNotFoundException(String code, String id) {
        this.id = id;
    }
}