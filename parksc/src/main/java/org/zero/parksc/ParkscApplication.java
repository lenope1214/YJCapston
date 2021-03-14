package org.zero.parksc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ParkscApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkscApplication.class, args);
    }

}
