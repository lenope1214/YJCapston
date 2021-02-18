package com.codesample.mymug.data;

import lombok.Data;

@Data
public class Point {

    private String userid;
    private int value;
    public Point() {}
    public Point(String userid, int value) {

        this.userid=userid;
        this.value=value;
    }

}