package com.jumanji.capston.controller.exception.ShopException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopHasExistException extends RuntimeException {
    private String id;

    public ShopHasExistException(String id) {
        this.id = id;
    }
}
