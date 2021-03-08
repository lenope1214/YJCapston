package com.jumanji.capston.controller.exception;

public class MemberNotFoundException extends RuntimeException{
    private String id;

    public MemberNotFoundException(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
