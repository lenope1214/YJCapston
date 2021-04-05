package com.jumanji.capston.controller.exception.UserException;

public class UserNotFoundException extends RuntimeException {
    private String id;

    public UserNotFoundException(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}