package com.fmattaperdomo.supplier.service;

import com.fmattaperdomo.supplier.service.domain.SupplierDomainService;
import com.fmattaperdomo.supplier.service.domain.SupplierDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public SupplierDomainService supplierDomainService() {
        return new SupplierDomainServiceImpl();
    }
}
