package com.jumanji.capston.service.exception.EmployeeException;

import com.jumanji.capston.service.exception.BasicException;

public class EmployeeHasExistException extends BasicException {
    public EmployeeHasExistException() {
        super("error-1003", "the ID in use");
    }

    public EmployeeHasExistException(String id) {
        super("error-1003", "these id has used - id : " + id);
    }

    public EmployeeHasExistException(String code, String message) {
        super(code, message);
    }


}