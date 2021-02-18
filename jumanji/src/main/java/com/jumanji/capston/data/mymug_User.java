package com.codesample.mymug.data;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="user")
public class User {
    @Id
    private String userid;
    @Column(nullable=false,length=100)
    private String name;
    @Column(nullable = false,length=100)
    private String password="1111";
    @Column(nullable = false,length=100)
    private String role="user";

    private int point;
    public User(){}
    public User(String userid, String name) {
        this.userid = userid;
        this.name = name;
    }
}