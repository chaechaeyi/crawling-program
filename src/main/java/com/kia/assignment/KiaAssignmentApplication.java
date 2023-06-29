package com.kia.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class KiaAssignmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(KiaAssignmentApplication.class, args);
    }

}
