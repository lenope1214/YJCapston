package com.jumanji.capston.service.exception;

import com.jumanji.capston.service.exception.MenuException.MenuHasExistException;
import com.jumanji.capston.service.exception.OrderMenuException.OrderMenuNotFoundException;
import com.jumanji.capston.service.exception.ShopException.NoShopListException;
import com.jumanji.capston.service.exception.ShopException.ShopHasExistException;
import com.jumanji.capston.service.exception.ShopException.ShopMissMatchException;
import com.jumanji.capston.service.exception.ShopException.ShopNotFoundException;
import com.jumanji.capston.service.exception.UserException.DoLoginExistException;
import com.jumanji.capston.service.exception.UserException.PasswordMissMatchException;
import com.jumanji.capston.service.exception.UserException.UserHasExistException;
import com.jumanji.capston.service.exception.UserException.UserNotFoundException;
import io.jsonwebtoken.MalformedJwtException;
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
                new ApiErrorResponse(ex);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserHasExistException.class)
    public ResponseEntity<ApiErrorResponse> idHandleException(UserHasExistException ex) {
        ApiErrorResponse response =
                new ApiErrorResponse(ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordMissMatchException.class)
    public ResponseEntity<ApiErrorResponse> pwHandleException(PasswordMissMatchException ex) {
        ApiErrorResponse response =
                new ApiErrorResponse(ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShopNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> shopException(ShopNotFoundException ex){
        ApiErrorResponse response =
                new ApiErrorResponse(ex);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ShopHasExistException.class)
    public ResponseEntity<ApiErrorResponse> shopException(ShopHasExistException ex){
        ApiErrorResponse response =
                new ApiErrorResponse(ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderMenuNotFoundException.class)
    public ResponseEntity<?> orderException(OrderMenuNotFoundException ex){
        ApiErrorResponse response=
                new ApiErrorResponse(ex);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MenuHasExistException.class)
    public ResponseEntity<?> menuException(MenuHasExistException ex){
        ApiErrorResponse response =
                new ApiErrorResponse(ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShopMissMatchException.class)
    public ResponseEntity<?> menuException(ShopMissMatchException ex){
        ApiErrorResponse response =
                new ApiErrorResponse(ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DoLoginExistException.class)
    public ResponseEntity<?> AuthenticationException(DoLoginExistException ex){
        ApiErrorResponse response =
                new ApiErrorResponse(ex);
//        new ApiErrorResponse("error-0000 : 권한없음", "로그인해주세요");
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NoShopListException.class)
    public ResponseEntity<?> shopException(NoShopListException ex){
        ApiErrorResponse response =
                new ApiErrorResponse(ex);
        return  new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> dataDuplicateKeyException(ConstraintViolationException ex){
        ApiErrorResponse response =
                new ApiErrorResponse("ORA-0001", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<?> jwtException(MalformedJwtException ex){
        ApiErrorResponse response =
                new ApiErrorResponse("token-0001", "토큰이 제대로 넘어오지 않습니다.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}