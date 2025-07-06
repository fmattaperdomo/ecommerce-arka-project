package com.fmattaperdomo.customer.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages = { "com.fmattaperdomo.customer.service.dataaccess", "com.fmattaperdomo.dataaccess"})
@EntityScan(basePackages = { "com.fmattaperdomo.customer.service.dataaccess", "com.fmattaperdomo.dataaccess" })
@SpringBootApplication(scanBasePackages = "com.fmattaperdomo")
public class CustomerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}

