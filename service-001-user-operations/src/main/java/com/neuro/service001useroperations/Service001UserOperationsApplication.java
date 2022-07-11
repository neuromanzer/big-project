package com.neuro.service001useroperations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class Service001UserOperationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(Service001UserOperationsApplication.class, args);
    }

}
