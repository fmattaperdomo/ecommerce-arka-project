package com.fmattaperdomo.supplier.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages = { "com.fmattaperdomo.supplier.service.dataaccess", "com.fmattaperdomo.dataaccess"})
@EntityScan(basePackages = { "com.fmattaperdomo.supplier.service.dataaccess", "com.fmattaperdomo.dataaccess" })
@SpringBootApplication(scanBasePackages = "com.fmattaperdomo")
public class SupplierServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SupplierServiceApplication.class, args);
    }
}

