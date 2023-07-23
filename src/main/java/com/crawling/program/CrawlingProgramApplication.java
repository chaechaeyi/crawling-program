package com.crawling.program;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CrawlingProgramApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrawlingProgramApplication.class, args);
    }

}
