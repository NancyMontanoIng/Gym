package com.example.reto3mangym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication //(scanBasePackages = "com.example.reto3mangym")
@EntityScan("com.example.reto3mangym.Model")
@EnableJpaRepositories("com.example.reto3mangym.Repository")
public class Reto3mangymApplication {

    public static void main(String[] args) {
        SpringApplication.run(Reto3mangymApplication.class, args);
    }

}
