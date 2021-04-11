package com.jumanji.capston.service.exception.BucketException;

import com.jumanji.capston.service.exception.BasicException;

public class BucketNotFoundException extends BasicException {
    public BucketNotFoundException(){
        super("error-not-defined", "Bucket Not Found !!");
    }
    public BucketNotFoundException(String id){
        super("error-not-defined", "Bucket Not Found with id : " + id);
    }
    public BucketNotFoundException(String code, String message) {
        super(code, message);
    }
}
