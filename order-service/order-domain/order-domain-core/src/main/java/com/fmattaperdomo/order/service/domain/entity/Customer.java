package com.fmattaperdomo.order.service.domain.entity;

import com.fmattaperdomo.domain.entity.AggregateRoot;
import com.fmattaperdomo.domain.valueobject.*;

import java.time.ZonedDateTime;

public class Customer extends AggregateRoot<CustomerId> {

    private Identification customerDocument;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole userRole;
    private Address customerAddress;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public Customer(Builder builder) {
        super.setId(builder.customerId);
        customerDocument = builder.customerDocument;
        firstName = builder.firstName;
        lastName = builder.lastName;
        email = builder.email;
        phone = builder.phone;
        userRole = builder.userRole;
        customerAddress = builder.customerAddress;
        createdAt = ZonedDateTime.now();
        updatedAt = ZonedDateTime.now();
    }

    public static Builder builder() {
        return new Builder();
    }

    public Identification getCustomerDocument() {
        return customerDocument;
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

    public Address getCustomerAddress() {
        return customerAddress;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static final class Builder {
        private CustomerId customerId;
        private Identification customerDocument;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private UserRole userRole;
        private Address customerAddress;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;

        public Builder() {
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder customerDocument(Identification val) {
            customerDocument = val;
            return this;
        }

        public Builder firstName(String val) {
            firstName = val;
            return this;
        }

        public Builder lastName(String val) {
            lastName = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder phone(String val) {
            phone = val;
            return this;
        }

        public Builder userRole(UserRole val) {
            userRole = val;
            return this;
        }

        public Builder customerAddress(Address val) {
            customerAddress = val;
            return this;
        }

        public Builder createdAt(ZonedDateTime val) {
            createdAt = val;
            return this;
        }

        public Builder updatedAt(ZonedDateTime val) {
            updatedAt = val;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
