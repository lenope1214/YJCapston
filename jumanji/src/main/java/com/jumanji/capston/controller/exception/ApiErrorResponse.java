package com.jumanji.capston.controller.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrorResponse {
    private String error;
    private String message;
//    private String status;
//    private String

    public ApiErrorResponse(String error, String message) {
        super();
        this.error = error;
        this.message = message;
    }

//    public ApiErrorResponse(String error, String message, String status) {
//        super();
//        this.error = error;
//        this.message = message;
//        this.status = status;
//    }
}
