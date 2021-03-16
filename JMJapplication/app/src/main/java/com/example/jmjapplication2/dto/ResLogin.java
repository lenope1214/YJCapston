package com.example.jmjapplication2.dto;

public class ResLogin {
    private int code;
    private MemberDTO data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public MemberDTO getData() {
        return data;
    }

    public void setData(MemberDTO data) {
        this.data = data;
    }
}
