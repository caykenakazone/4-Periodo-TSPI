package com.iftm.imobiliarianosqql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ImobiliariaNosqqlApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImobiliariaNosqqlApplication.class, args);
    }

}
