package com.jumanji.capston.service.exception.employeeException;

import com.jumanji.capston.service.exception.BasicException;

public class EmployeeNotFoundException extends BasicException {
    public EmployeeNotFoundException(){
        super("error-901", "Not Found User with id");
    }

    public EmployeeNotFoundException(String id){
        super("error-901", "Not Found User with id : " + id);
    }

    public EmployeeNotFoundException(String code, String message) {
        super(code, message);
    }


}