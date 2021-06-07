package com.jumanji.capston.service.exception.employeeException;

import com.jumanji.capston.service.exception.BasicException;

public class EmployeeDoesNotStartException extends BasicException {
    public EmployeeDoesNotStartException() {
        super("error-912", "직원이 출근등록을 하지 않았습니다.");
    }

    public EmployeeDoesNotStartException(String id) {
        super("error-912", "직원이 출근등록을 하지 않았습니다. id : " + id);
    }

    public EmployeeDoesNotStartException(String code, String message) {
        super(code, message);
    }


}