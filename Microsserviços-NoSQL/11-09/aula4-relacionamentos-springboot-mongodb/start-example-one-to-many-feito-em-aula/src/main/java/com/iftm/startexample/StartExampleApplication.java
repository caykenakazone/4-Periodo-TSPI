package com.iftm.startexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class StartExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartExampleApplication.class, args);
    }
}
