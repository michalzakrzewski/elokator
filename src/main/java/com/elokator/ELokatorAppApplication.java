package com.elokator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ELokatorAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ELokatorAppApplication.class, args);
    }
}
