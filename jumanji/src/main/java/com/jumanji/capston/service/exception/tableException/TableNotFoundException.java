package com.jumanji.capston.service.exception.tableException;

import com.jumanji.capston.service.exception.BasicException;

public class TableNotFoundException extends BasicException {
    public TableNotFoundException() {
        super("error-5001", "The table not found");
    }
    public TableNotFoundException(String code, String message) {
        super(code, message);
    }
}
