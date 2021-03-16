package com.example.restapi.model;

public class ResLogin {
    private int code;
    private Member data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public Member getData() {
        return data;
    }

    public void setData(Member data) {
        this.data = data;
    }
}
