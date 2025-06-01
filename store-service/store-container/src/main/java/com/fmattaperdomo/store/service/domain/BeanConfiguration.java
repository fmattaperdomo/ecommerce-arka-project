package com.fmattaperdomo.store.service.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public StoreDomainService storeDomainService() {
        return new StoreDomainServiceImpl();
    }

}
