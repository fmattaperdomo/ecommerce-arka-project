package com.fmattaperdomo.order.service.domain.ports.output.repository;

import com.fmattaperdomo.order.service.domain.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    Optional<Customer> findCustomer(UUID customerId);

    Customer save(Customer customer);
}