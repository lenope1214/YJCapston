package com.jumanji.capston.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BasicException extends RuntimeException{
    private String code;
    private String message;
}
