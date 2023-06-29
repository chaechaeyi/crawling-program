package com.kia.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class KIATestApplication {
    public static void main(String[] args) {
        SpringApplication.run(KIATestApplication.class, args);
    }

}
