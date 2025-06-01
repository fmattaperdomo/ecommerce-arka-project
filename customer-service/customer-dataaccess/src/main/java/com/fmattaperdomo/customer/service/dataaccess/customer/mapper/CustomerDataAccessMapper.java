package com.fmattaperdomo.customer.service.dataaccess.customer.mapper;

import com.fmattaperdomo.customer.service.dataaccess.customer.entity.CustomerEntity;
import com.fmattaperdomo.customer.service.domain.entity.Customer;
import com.fmattaperdomo.domain.valueobject.CustomerId;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {
    public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return new Customer(new CustomerId(customerEntity.getId()),
                customerEntity.getIdentification(),
                customerEntity.getFirstName(),
                customerEntity.getLastName(),
                customerEntity.getEmail(),
                customerEntity.getPhone(),
                customerEntity.getRole(),
                customerEntity.getAddress()
        );
    }

    public CustomerEntity customerToCustomerEntity(Customer customer) {
        return CustomerEntity.builder()
                .id(customer.getId().getValue())
                .identification(customer.getIdentification())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .role(customer.getRole())
                .address(customer.getAddress())
                .build();
    }
}
