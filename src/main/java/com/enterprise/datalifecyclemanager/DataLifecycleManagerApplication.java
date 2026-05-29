package com.enterprise.datalifecyclemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@org.springframework.scheduling.annotation.EnableScheduling
public class DataLifecycleManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataLifecycleManagerApplication.class, args);
    }
}
