package com.life_protocol.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LifeProtocolApplication {

    public static void main(String[] args) {
        SpringApplication.run(LifeProtocolApplication.class, args);
        System.out.println("Welcome to Life Protocol!");
    }

}