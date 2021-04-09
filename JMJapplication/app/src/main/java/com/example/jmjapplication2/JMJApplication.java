package com.example.jmjapplication2;

import android.app.Application;

public class JMJApplication extends Application {
    private String id;

    private String role;

    private String jwt;

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJwt() {
        return jwt;
    }


    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
