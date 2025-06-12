package com.fmattaperdomo.order.service.dataaccess.customer.mapper;

import com.fmattaperdomo.domain.valueobject.Address;
import com.fmattaperdomo.domain.valueobject.CustomerId;
import com.fmattaperdomo.domain.valueobject.Identification;
import com.fmattaperdomo.order.service.dataaccess.customer.entity.CustomerAddressEntity;
import com.fmattaperdomo.order.service.dataaccess.customer.entity.CustomerEntity;
import com.fmattaperdomo.order.service.dataaccess.customer.entity.CustomerIdentificationEntity;
import com.fmattaperdomo.order.service.domain.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {
    public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return Customer.builder()
                .customerId(new CustomerId(customerEntity.getId()))
                .firstName(customerEntity.getFirstName())
                .lastName(customerEntity.getLastName())
                .email(customerEntity.getEmail())
                .phone(customerEntity.getPhone())
                .customerDocument(identificationEntityToCustomerDocument(customerEntity.getCustomerIdentification()))
                .customerAddress(addressEntityToCustomerAddress(customerEntity.getCustomerAddress()))
                .build();

    }

    public CustomerEntity customerToCustomerEntity(Customer customer) {
        return CustomerEntity.builder()
                .id(customer.getId().getValue())
                .lastName(customer.getLastName())
                .firstName(customer.getFirstName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .userRole(customer.getUserRole())
                .customerIdentification(customerIdentificationToCustomerIdentificationEntity(customer.getCustomerDocument()))
                .customerAddress(customerAddressToAddressEntity(customer.getCustomerAddress()))
                .build();
    }

    private CustomerAddressEntity customerAddressToAddressEntity(Address customerAddress) {
        return CustomerAddressEntity.builder()
                .id(customerAddress.getId())
                .street(customerAddress.getStreet())
                .city(customerAddress.getCity())
                .state(customerAddress.getState())
                .country(customerAddress.getCountry())
                .zipcode(customerAddress.getZipcode())
                .build();
    }

    private CustomerIdentificationEntity customerIdentificationToCustomerIdentificationEntity(Identification customerDocument) {
        return CustomerIdentificationEntity.builder()
                .id(customerDocument.getId())
                .typeIdentification(customerDocument.getTypeIdentification())
                .documentNumber(customerDocument.getDocumentNumber())
                .build();
    }

    private Identification identificationEntityToCustomerDocument(CustomerIdentificationEntity customerIdentication) {
        return new Identification(customerIdentication.getId(),
                customerIdentication.getTypeIdentification(),
                customerIdentication.getDocumentNumber());
    }

    private Address addressEntityToCustomerAddress(CustomerAddressEntity customerAddress) {
        return new Address(customerAddress.getId(),
                customerAddress.getStreet(),
                customerAddress.getCity(),
                customerAddress.getState(),
                customerAddress.getCountry(),
                customerAddress.getZipcode());
    }

}
