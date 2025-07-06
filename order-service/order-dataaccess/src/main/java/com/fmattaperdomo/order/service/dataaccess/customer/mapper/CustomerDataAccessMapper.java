package com.fmattaperdomo.order.service.dataaccess.customer.mapper;

import com.fmattaperdomo.domain.valueobject.CustomerId;
import com.fmattaperdomo.order.service.dataaccess.customer.entity.CustomerEntity;
import com.fmattaperdomo.order.service.domain.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {
    public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return Customer.builder()
                .customerId(new CustomerId(customerEntity.getId()))
                .typeIdentification(customerEntity.getTypeIdentification())
                .documentNumber(customerEntity.getDocumentNumber())
                .firstName(customerEntity.getFirstName())
                .lastName(customerEntity.getLastName())
                .email(customerEntity.getEmail())
                .phone(customerEntity.getPhone())
                .userRole(customerEntity.getUserRole())
                .build();

    }

    public CustomerEntity customerToCustomerEntity(Customer customer) {
        return CustomerEntity.builder()
                .id(customer.getId().getValue())
                .typeIdentification(customer.getTypeIdentification())
                .documentNumber(customer.getDocumentNumber())
                .lastName(customer.getLastName())
                .firstName(customer.getFirstName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .userRole(customer.getUserRole())
                .build();
    }
}
