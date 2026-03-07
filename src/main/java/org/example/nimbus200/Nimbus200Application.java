package org.example.nimbus200;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Nimbus200Application {

    public static void main(String[] args) {
        SpringApplication.run(Nimbus200Application.class, args);
    }

}
