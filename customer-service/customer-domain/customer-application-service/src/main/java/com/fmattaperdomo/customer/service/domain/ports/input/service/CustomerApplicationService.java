package com.fmattaperdomo.customer.service.domain.ports.input.service;

import com.fmattaperdomo.customer.service.domain.create.CreateCustomerCommand;
import com.fmattaperdomo.customer.service.domain.create.CreateCustomerResponse;

import jakarta.validation.Valid;

public interface CustomerApplicationService {

    CreateCustomerResponse createCustomer(@Valid CreateCustomerCommand createCustomerCommand);

}
