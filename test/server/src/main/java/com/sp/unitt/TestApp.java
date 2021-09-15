package com.sp.unitt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.sp.unitt.config"
})
public class TestApp {

    public static void main(String[] args) {
        SpringApplication.run(TestApp.class, args);
    }

}
