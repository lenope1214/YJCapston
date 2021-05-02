package com.jumanji.capston.service.exception.EmployeeException;

import com.jumanji.capston.service.exception.BasicException;

public class EmployeeNotFoundException extends BasicException {
    public EmployeeNotFoundException(){
        super("error-0001", "Not Found User with id");
    }

    public EmployeeNotFoundException(String id){
        super("0001", "Not Found User with id : " + id);
    }

    public EmployeeNotFoundException(String code, String message) {
        super(code, message);
    }


}