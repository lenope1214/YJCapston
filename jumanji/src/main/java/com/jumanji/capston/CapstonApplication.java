package com.jumanji.capston;

import com.jumanji.capston.config.IamportConfig;
import com.jumanji.capston.config.StorageConfig;
import com.jumanji.capston.storage.StorageServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({StorageConfig.class, IamportConfig.class})
public class CapstonApplication {

    public static void main(String[] args) {
        SpringApplication.run(CapstonApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageServiceImpl storageService) {
        return (args) -> {
//            storageService.deleteAll();
            storageService.init();
        };
    }


}
