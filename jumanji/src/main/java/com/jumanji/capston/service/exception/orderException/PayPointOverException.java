package com.jumanji.capston.service.exception.orderException;

import com.jumanji.capston.service.exception.BasicException;

public class PayPointOverException extends BasicException {
    public PayPointOverException(){
        super("error-705", "사용포인트가 남은 포인트보다 큽니다.");
    }
    public PayPointOverException(int point){
        super("error-705", "사용포인트가 남은 포인트보다 큽니다." + point);
    }
}
