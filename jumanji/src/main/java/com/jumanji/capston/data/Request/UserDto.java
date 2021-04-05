package com.jumanji.capston.data.Request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserDto {
    private String id;
    private String password;
    private String name;
    private String email;
    private String address;
    private String addressDetail;
    private String phone;
    private String role;

    public static class Info{
        private String id;
        private String password;
        private String name;
        private String email;
        private String address;
        private String addressDetail;
        private String phone;
        private String role;
    }

    @Getter
    @Setter
    public static class Request{
        private String id;
    }
}
