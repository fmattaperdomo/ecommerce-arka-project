package com.fmattaperdomo.customer.service.domain.mapper;

import com.fmattaperdomo.customer.service.domain.create.CreateCustomerCommand;
import com.fmattaperdomo.customer.service.domain.create.CreateCustomerResponse;
import com.fmattaperdomo.customer.service.domain.entity.Customer;
import com.fmattaperdomo.domain.valueobject.CustomerId;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataMapper {

    public Customer createCustomerCommandToCustomer(CreateCustomerCommand createCustomerCommand) {
        return new Customer(new CustomerId(createCustomerCommand.getCustomerId()),
                createCustomerCommand.getTypeIdentification(),
                createCustomerCommand.getDocumentNumber(),
                createCustomerCommand.getFirstName(),
                createCustomerCommand.getLastName(),
                createCustomerCommand.getEmail(),
                createCustomerCommand.getPhone(),
                createCustomerCommand.getUserRole()
        );
    }

    public CreateCustomerResponse customerToCreateCustomerResponse(Customer customer, String message) {
        return new CreateCustomerResponse(customer.getId().getValue(), message);
    }
}
