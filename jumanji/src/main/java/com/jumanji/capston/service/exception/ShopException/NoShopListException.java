package com.jumanji.capston.service.exception.ShopException;

import com.jumanji.capston.service.exception.BasicException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoShopListException extends BasicException {

    public NoShopListException(){
        super("error-1004", "등록된 식당이 하나도 없습니다.");
    }
    public NoShopListException(String code, String message) {
        super(code, message);
    }
}
