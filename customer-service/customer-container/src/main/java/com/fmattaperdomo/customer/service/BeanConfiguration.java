package com.fmattaperdomo.customer.service;

import com.fmattaperdomo.customer.service.domain.CustomerDomainService;
import com.fmattaperdomo.customer.service.domain.CustomerDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public CustomerDomainService customerDomainService() {
        return new CustomerDomainServiceImpl();
    }
}
