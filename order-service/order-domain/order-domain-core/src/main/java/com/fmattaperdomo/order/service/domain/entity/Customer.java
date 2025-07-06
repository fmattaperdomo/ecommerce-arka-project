package com.fmattaperdomo.order.service.domain.entity;

import com.fmattaperdomo.domain.entity.AggregateRoot;
import com.fmattaperdomo.domain.valueobject.*;

public class Customer extends AggregateRoot<CustomerId> {

    private String typeIdentification;
    private String documentNumber;     
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String userRole;

    public Customer(Builder builder) {
        super.setId(builder.customerId);
        typeIdentification = builder.typeIdentification;
        documentNumber = builder.documentNumber;
        firstName = builder.firstName;
        lastName = builder.lastName;
        email = builder.email;
        phone = builder.phone;
        userRole = builder.userRole;
    }

    public Customer() {
    }
    
    public Customer(CustomerId customerId) {
        super.setId(customerId);
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getTypeIdentification() {
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

    public String getUserRole() {
        return userRole;
    }

    public static final class Builder {
        private CustomerId customerId;
        private String typeIdentification;
        private String documentNumber;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String userRole;

        public Builder() {
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder typeIdentification(String val) {
            typeIdentification = val;
            return this;
        }

        public Builder documentNumber(String val) {
            documentNumber = val;
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

        public Builder userRole(String val) {
            userRole = val;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
