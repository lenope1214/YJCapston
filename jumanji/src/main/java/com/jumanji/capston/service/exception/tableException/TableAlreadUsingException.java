package com.jumanji.capston.service.exception.tableException;

import com.jumanji.capston.service.exception.BasicException;

public class TableAlreadUsingException extends BasicException {
    public TableAlreadUsingException() {
        super("error-5003", "테이블이 사용중입니다.");
    }
}
