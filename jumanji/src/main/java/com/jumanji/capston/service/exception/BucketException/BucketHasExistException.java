package com.jumanji.capston.service.exception.BucketException;

import com.jumanji.capston.service.exception.BasicException;

public class BucketHasExistException extends BasicException {
    public BucketHasExistException(){
        super("error-not-defined", "Bucket Has Exist same id !!");
    }
    public BucketHasExistException(String id){
        super("error-not-defined", "Bucket Has Exist same id with id : " + id);
    }
    public BucketHasExistException(String code, String message) {
        super(code, message);
    }
}
