package com.fmattaperdomo.customer.service.domain.entity;

import com.fmattaperdomo.domain.entity.AggregateRoot;
import com.fmattaperdomo.domain.valueobject.Address;
import com.fmattaperdomo.domain.valueobject.CustomerId;
import com.fmattaperdomo.domain.valueobject.Identification;
import com.fmattaperdomo.domain.valueobject.UserRole;

import java.time.LocalDateTime;

public class Customer extends AggregateRoot<CustomerId> {
    private final Identification identification;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
    private final UserRole role;
    private final Address address;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Customer(CustomerId customerId, Identification identification, String firstName, String lastName, String email, String phone, UserRole role, Address address) {
        this.setId(customerId);
        this.identification = identification;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.address = address;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Identification getIdentification() {
        return identification;
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

    public UserRole getRole() {
        return role;
    }

    public Address getAddress() {
        return address;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}


