package com.jumanji.capston.service.exception;

import com.jumanji.capston.service.exception.MenuException.MenuHasExistException;
import com.jumanji.capston.service.exception.OrderException.OrderNotFoundException;
import com.jumanji.capston.service.exception.ShopException.ShopMissMatchException;
import com.jumanji.capston.service.exception.ShopException.ShopNotFoundException;
import com.jumanji.capston.service.exception.UserException.PasswordMissMatchException;
import com.jumanji.capston.service.exception.UserException.UserNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
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
                new ApiErrorResponse(ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShopNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> shopExistException(ShopNotFoundException ex){
        ApiErrorResponse response =
                new ApiErrorResponse(ex);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<?> orderNotFoundException(OrderNotFoundException ex){
        ApiErrorResponse response=
                new ApiErrorResponse(ex);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MenuHasExistException.class)
    public ResponseEntity<?> menuAlreadUsedException(MenuHasExistException ex){
        ApiErrorResponse response =
                new ApiErrorResponse(ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShopMissMatchException.class)
    public ResponseEntity<?> menuAlreadUsedException(ShopMissMatchException ex){
        ApiErrorResponse response =
                new ApiErrorResponse(ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> dataDuplicateKeyException(ConstraintViolationException ex){
        ApiErrorResponse response =
                new ApiErrorResponse("ORA-00001", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}