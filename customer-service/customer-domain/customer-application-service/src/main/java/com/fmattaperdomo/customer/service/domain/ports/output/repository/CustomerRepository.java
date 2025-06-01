package com.fmattaperdomo.customer.service.domain.ports.output.repository;

import com.fmattaperdomo.customer.service.domain.entity.Customer;

public interface CustomerRepository {

    Customer createCustomer(Customer customer);
}

