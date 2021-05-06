package com.jumanji.capston.service.exception;

import com.jumanji.capston.service.exception.auth.ForbiddenException;
import com.jumanji.capston.service.exception.employeeException.EmployeeHasExistException;
import com.jumanji.capston.service.exception.employeeException.EmployeeNotFoundException;
import com.jumanji.capston.service.exception.menuException.MenuHasExistException;
import com.jumanji.capston.service.exception.myException.MyNonUniqueResultException;
import com.jumanji.capston.service.exception.orderException.OrderNotFoundException;
import com.jumanji.capston.service.exception.orderMenuException.OrderMenuNotFoundException;
import com.jumanji.capston.service.exception.reviewException.ReviewHasExistException;
import com.jumanji.capston.service.exception.shopException.NoShopListException;
import com.jumanji.capston.service.exception.shopException.ShopHasExistException;
import com.jumanji.capston.service.exception.shopException.ShopMissMatchException;
import com.jumanji.capston.service.exception.shopException.ShopNotFoundException;
import com.jumanji.capston.service.exception.userException.DoLoginExistException;
import com.jumanji.capston.service.exception.userException.LoginFailedException;
import com.jumanji.capston.service.exception.userException.UserHasExistException;
import com.jumanji.capston.service.exception.userException.UserNotFoundException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.spi.SQLExceptionConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.NonUniqueResultException;

@ControllerAdvice @RequiredArgsConstructor
public class ApiExceptionHandler{


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

    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<ApiErrorResponse> pwHandleException(LoginFailedException ex) {
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
                new ApiErrorResponse("ORA-0001", ex.getConstraintName(), ex.getSQL());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<?> jwtException(MalformedJwtException ex){
        ApiErrorResponse response =
                new ApiErrorResponse("token-0001", "토큰이 제대로 넘어오지 않습니다.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<?> missingHeader(MissingRequestHeaderException ex){
        ApiErrorResponse response =
                new ApiErrorResponse("badRequest-0001", "헤더가 제대로 넘어오지 않습니다.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> empNotFoundException(EmployeeNotFoundException ex) {
        ApiErrorResponse response =
                new ApiErrorResponse(ex);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeHasExistException.class)
    public ResponseEntity<ApiErrorResponse> empHasExistException(EmployeeHasExistException ex) {
        ApiErrorResponse response =
                new ApiErrorResponse(ex);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CanNotBeZero.class)
    public ResponseEntity<ApiErrorResponse> canNotBeZero(CanNotBeZero ex) {
        ApiErrorResponse response =
                new ApiErrorResponse(ex.getCode(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiErrorResponse> forbidden(ForbiddenException ex) {
        ApiErrorResponse response =
                new ApiErrorResponse(ex);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> orderNotFound(OrderNotFoundException ex) {
        ApiErrorResponse response =
                new ApiErrorResponse(ex);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MyNullPointerException.class)
    public ResponseEntity<ApiErrorResponse> nullPointException(MyNullPointerException ex) {
        ApiErrorResponse response =
                new ApiErrorResponse(ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NonUniqueResultException.class)
    public ResponseEntity<ApiErrorResponse> myNonUniqueResultException(NonUniqueResultException ex) {
        ApiErrorResponse response =
                new ApiErrorResponse("error-value-non-unique", "유니크 제약조건 위반");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReviewHasExistException.class)
    public ResponseEntity<ApiErrorResponse> myNonUniqueResultException(ReviewHasExistException ex) {
        ApiErrorResponse response =
                new ApiErrorResponse(ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}