package com.jumanji.capston.service.exception.orderException;

import com.jumanji.capston.service.exception.BasicException;

public class PayAmountOverException extends BasicException {
    public PayAmountOverException(){
        super("error-508", "결제 요청 금액이 남은 결제액보다 큽니다.");
    }
    public PayAmountOverException(int amount){
        super("error-508", "결제 요청 금액이 남은 결제액보다 큽니다." + amount);
    }
}
