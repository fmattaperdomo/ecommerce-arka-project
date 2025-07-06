package com.fmattaperdomo.customer.service.domain.entity;

import com.fmattaperdomo.domain.entity.AggregateRoot;
import com.fmattaperdomo.domain.valueobject.CustomerId;
import com.fmattaperdomo.domain.valueobject.TypeIdentification;
import com.fmattaperdomo.domain.valueobject.UserRole;


public class Customer extends AggregateRoot<CustomerId> {
    private final TypeIdentification typeIdentification;
    private final String documentNumber;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
    private final UserRole userRole;

    public Customer(CustomerId customerId, TypeIdentification typeIdentification, String documentNumber, String firstName, String lastName, String email, String phone, UserRole userRole) {
        this.setId(customerId);
        this.typeIdentification = typeIdentification;
        this.documentNumber = documentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.userRole = userRole;
    }

    public TypeIdentification getTypeIdentification() {
        return typeIdentification;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public UserRole getUserRole() {
        return userRole;
    }
}