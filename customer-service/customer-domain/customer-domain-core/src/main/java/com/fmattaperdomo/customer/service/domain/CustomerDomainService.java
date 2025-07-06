package com.fmattaperdomo.customer.service.domain;

import com.fmattaperdomo.customer.service.domain.entity.Customer;
import com.fmattaperdomo.customer.service.domain.event.CustomerCreatedEvent;

public interface CustomerDomainService {

    CustomerCreatedEvent validateAndInitiateCustomer(Customer customer);

}

