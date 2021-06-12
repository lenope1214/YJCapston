package com.jumanji.capston.service.exception.tableException;

import com.jumanji.capston.service.exception.BasicException;

public class TableNotFoundException extends BasicException {
    public TableNotFoundException() {
        super("error-5001", "테이블을 찾을 수 없습니다.");
    }
    public TableNotFoundException(String code, String message) {
        super(code, message);
    }
}
