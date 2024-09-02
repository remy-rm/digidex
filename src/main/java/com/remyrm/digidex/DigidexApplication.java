package com.remyrm.digidex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.remyrm.digidex")
public class DigidexApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigidexApplication.class, args);
    }

}
