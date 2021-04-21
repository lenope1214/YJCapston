package com.jumanji.capston.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter @Setter @NoArgsConstructor
@ConfigurationProperties(prefix="iamport")
public class IamportConfig {
    private String key;
    private String secret;
}
