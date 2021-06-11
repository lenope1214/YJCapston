package com.jumanji.capston.service.exception.myException;

import com.jumanji.capston.service.exception.BasicException;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.NonUniqueResultException;

@Getter @Setter
public class MyNonUniqueResultException extends  NonUniqueResultException{
    private String code;
    private String message;

    public MyNonUniqueResultException(String message) {
        super(999);
        this.code = "error-value-non-unique";
        this.message = message;
    }

    public MyNonUniqueResultException(String code, String message) {
        super(999);
        this.code = code;
        this.message = message;
    }
//    public class MyNonUniqueResultException extends BasicException{
//
//        public MyNonUniqueResultException(String message) {
//            super("error-not-unique-value", message);
//        }
//
//        public MyNonUniqueResultException(String code, String message) {
//            super(code, message);
//        }


//
//
//    public void NonUniqueResultException(String message){
//        this.code = "error-not-unique";
//        this.message = message;
//    }
//
//    /**
//     * Constructs a NonUniqueResultException.
//     *
//     * @param resultCount The number of actual results.
//     */
//    public MyNonUniqueResultException(int resultCount) {
//        super(resultCount);
//    }
//
//    public MyNonUniqueResultException(String code, String message, int resultCount){
//        super(resultCount);
//        this.code = code;
//        this.message = message;
//    }
}
