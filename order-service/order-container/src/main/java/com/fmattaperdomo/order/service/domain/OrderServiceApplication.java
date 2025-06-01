package com.fmattaperdomo.order.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "com.fmattaperdomo.order.service.dataaccess", "com.fmattaperdomo.dataaccess" })
@EntityScan(basePackages = { "com.fmattaperdomo.order.service.dataaccess", "com.fmattaperdomo.dataaccess"})
@SpringBootApplication(scanBasePackages = "com.fmattaperdomo")
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}

