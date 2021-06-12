package com.jumanji.capston.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CanNotBeZero extends RuntimeException{
    private String code = "error-zero";
    private String message = "can't be zero";

    public CanNotBeZero(String message){
        this.message = message;
    }
}
