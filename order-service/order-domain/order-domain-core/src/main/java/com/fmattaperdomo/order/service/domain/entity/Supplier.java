package com.fmattaperdomo.order.service.domain.entity;

import com.fmattaperdomo.domain.entity.AggregateRoot;
import com.fmattaperdomo.domain.valueobject.*;

import java.time.ZonedDateTime;

public class Supplier extends AggregateRoot<SupplierId> {
    private Identification supplierDocument;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole userRole;
    private Address supplierAddress;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    private Supplier(Builder builder) {
        super.setId(builder.supplierId);
        supplierDocument = builder.supplierDocument;
        firstName = builder.firstName;
        lastName = builder.lastName;
        email = builder.email;
        phone = builder.phone;
        userRole = builder.userRole;
        supplierAddress = builder.supplierAddress;
        createdAt = builder.createdAt;
        updatedAt = builder.updatedAt;
    }


    public Identification getSupplierDocument() {
        return supplierDocument;
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

    public Address getSupplierAddress() {
        return supplierAddress;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static final class Builder {
        private SupplierId supplierId;
        private Identification supplierDocument;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private UserRole userRole;
        private Address supplierAddress;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;

        private Builder() {
        }

        public Builder supplierId(SupplierId val) {
            supplierId = val;
            return this;
        }

        public Builder supplierDocument(Identification val) {
            supplierDocument = val;
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

        public Builder supplierAddress(Address val) {
            supplierAddress = val;
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

        public Supplier build() {
            return new Supplier(this);
        }
    }
}
