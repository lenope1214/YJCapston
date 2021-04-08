package com.jumanji.capston.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.apache.bcel.classfile.Code;

@Getter
@Setter
@AllArgsConstructor
public class BasicException extends RuntimeException{
    private String code;
    private String message;
}
