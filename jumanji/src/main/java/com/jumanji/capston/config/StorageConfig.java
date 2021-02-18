package com.codesample.mymug.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "storage")
@Component
public class StorageConfig {
    private String location;
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location=location;
    }
}