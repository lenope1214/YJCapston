package com.example.jmjapplication2;

import android.app.Application;

public class JMJApplication extends Application {
    private String userid;

    private String role;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
