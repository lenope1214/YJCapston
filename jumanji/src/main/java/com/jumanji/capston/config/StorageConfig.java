package com.jumanji.capston.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="storage")
//@Configuration
public class StorageConfig {
    private String location;

    public StorageConfig() {
//        System.out.println("StorageConfig created...");
//        this.location = location;
    }
    public String getLocation() {
//        System.out.println("StorageConfig location:" + location);
        return location;
    }
    public void setLocation(String location) {
//        System.out.println("StorageConfig setLocation:" + location);
        this.location=location;
    }
}