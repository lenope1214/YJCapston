package com.jumanji.capston.service.exception;

import com.jumanji.capston.data.Tab;
import com.jumanji.capston.service.exception.MenuException.MenuHasExistException;
import com.jumanji.capston.service.exception.OrderMenuException.OrderMenuNotFoundException;
import com.jumanji.capston.service.exception.ShopException.ShopMissMatchException;
import com.jumanji.capston.service.exception.ShopException.ShopNotFoundException;
import com.jumanji.capston.service.exception.TableException.TableHasExistException;
import com.jumanji.capston.service.exception.TableException.TableNotFoundException;
import com.jumanji.capston.service.exception.UserException.DoLoginExistException;
import com.jumanji.capston.service.exception.UserException.PasswordMissMatchException;
import com.jumanji.capston.service.exception.UserException.UserNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ApiErrorResponse> missingHeader(MissingRequestHeaderException ex) {
        ApiErrorResponse response = null;
        if (ex.getMessage().contains("authorization")) {
            response =
                    new ApiErrorResponse("code-header", "does not exist require header authorization parameter");
        }else{
            response =
                    new ApiErrorResponse("code-header", "does not exist require header authorization parameter");

        }


        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

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
    public ResponseEntity<ApiErrorResponse> shopExistException(ShopNotFoundException ex) {
        ApiErrorResponse response =
                new ApiErrorResponse(ex);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderMenuNotFoundException.class)
    public ResponseEntity<?> orderNotFoundException(OrderMenuNotFoundException ex) {
        ApiErrorResponse response =
                new ApiErrorResponse(ex);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MenuHasExistException.class)
    public ResponseEntity<?> menuAlreadUsedException(MenuHasExistException ex) {
        ApiErrorResponse response =
                new ApiErrorResponse(ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShopMissMatchException.class)
    public ResponseEntity<?> menuAlreadUsedException(ShopMissMatchException ex) {
        ApiErrorResponse response =
                new ApiErrorResponse(ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> dataDuplicateKeyException(ConstraintViolationException ex) {
        ApiErrorResponse response =
                new ApiErrorResponse("ORA-00001", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DoLoginExistException.class)
    public ResponseEntity<?> AuthenticationException(DoLoginExistException ex) {
        ApiErrorResponse response =
                new ApiErrorResponse(ex);
//        new ApiErrorResponse("error-0000 : 권한없음", "로그인해주세요");
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TableHasExistException.class)
    public ResponseEntity<?> TableHasExistException(TableHasExistException ex){
        ApiErrorResponse response =
                new ApiErrorResponse(ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TableNotFoundException.class)
    public ResponseEntity<?> TableNotFoundException(TableNotFoundException ex){
        ApiErrorResponse response =
                new ApiErrorResponse(ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}