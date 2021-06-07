package com.jumanji.capston.service.exception;

public class MyNullPointerException extends BasicException {
    public MyNullPointerException() {
        super("error-null", "null 값이 존재합니다.");
    }

    public MyNullPointerException(String origin, String varType, String varName) {
        super("error-null",
                "null 값이 존재합니다.\n" +
                        "발생지 : " + origin +
                        varType + " : " + varName);
    }
}
