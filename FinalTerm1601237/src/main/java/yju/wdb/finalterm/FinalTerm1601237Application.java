package yju.wdb.finalterm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FinalTerm1601237Application {

    public static void main(String[] args) {
        SpringApplication.run(FinalTerm1601237Application.class, args);
    }

}
