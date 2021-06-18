package com.jumanji.capston.service.exception.tableException;

import com.jumanji.capston.service.exception.BasicException;

public class TableHasExistException extends BasicException {
    public TableHasExistException() {
        super("error-5002", "The table id is exist");
    }
    public TableHasExistException(String code, String message) {
        super(code, message);
    }
}
