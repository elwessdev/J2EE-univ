package com.apps.examlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ExamLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamLabApplication.class, args);
    }

}
