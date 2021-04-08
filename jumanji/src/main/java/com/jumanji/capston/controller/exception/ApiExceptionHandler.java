package com.jumanji.capston.controller.exception;

import com.jumanji.capston.controller.exception.OrderException.OrderNotFoundException;
import com.jumanji.capston.controller.exception.ShopException.ShopNotFoundException;
import com.jumanji.capston.controller.exception.UserException.PasswordMissMatchException;
import com.jumanji.capston.controller.exception.UserException.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> idHandleException(UserNotFoundException ex) {
        ApiErrorResponse response =
                new ApiErrorResponse("error-0001", "No User is found by ID");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordMissMatchException.class)
    public ResponseEntity<ApiErrorResponse> pwHandleException(PasswordMissMatchException ex) {
        ApiErrorResponse response =
                new ApiErrorResponse("error-0002", "faild login : " + ex.getPassword());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ShopNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> shopExistException(ShopNotFoundException ex){
        ApiErrorResponse response =
                new ApiErrorResponse("error-1001", "not exist shop id");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<?> orderNotFoundException(OrderNotFoundException ex){
        ApiErrorResponse response=
                new ApiErrorResponse("error-3001", "not found order");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}