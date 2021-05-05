package com.jumanji.capston.service.exception.employeeException;

import com.jumanji.capston.service.exception.BasicException;

public class EmployeeHasExistException extends BasicException {
    public EmployeeHasExistException() {
        super("error-902", "the ID in use");
    }

    public EmployeeHasExistException(String id) {
        super("error-902", "these id has used - id : " + id);
    }

    public EmployeeHasExistException(String code, String message) {
        super(code, message);
    }


}