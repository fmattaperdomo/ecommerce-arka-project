package com.fmattaperdomo.order.service.domain.entity;

import com.fmattaperdomo.domain.entity.AggregateRoot;
import com.fmattaperdomo.domain.valueobject.Address;
import com.fmattaperdomo.domain.valueobject.Contact;
import com.fmattaperdomo.domain.valueobject.StoreId;

import java.time.ZonedDateTime;
import java.util.List;

public class Store extends AggregateRoot<StoreId> {
    private String name;
    private String description;
    private Address storeAddress;
    private Contact storeContact;
    private Boolean active;
    private final List<ProductStore> productsStore;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    private Store(Builder builder) {
        super.setId(builder.storeId);
        name = builder.name;
        description = builder.description;
        storeAddress = builder.storeAddress;
        storeContact = builder.storeContact;
        active = builder.active;
        productsStore = builder.productsStore;
        createdAt = ZonedDateTime.now();
        updatedAt = ZonedDateTime.now();
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Address getStoreAddress() {
        return storeAddress;
    }

    public Contact getStoreContact() {
        return storeContact;
    }

    public Boolean isActive() {
        return active;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<ProductStore> getProductsStore() {
        return productsStore;
    }

    public static final class Builder {
        private StoreId storeId;
        private String name;
        private String description;
        private Address storeAddress;
        private Contact storeContact;
        private boolean active;
        private List<ProductStore> productsStore;

        private Builder() {
        }
        public Builder storeId(StoreId val) {
            storeId = val;
            return this;
        }
        public Builder productsStore(List<ProductStore> val) {
            productsStore = val;
            return this;
        }
        public Builder name(String val) {
            name = val;
            return this;
        }
        public Builder description(String val) {
            description = val;
            return this;
        }
        public Builder storeAddress(Address val) {
            storeAddress = val;
            return this;
        }
        public Builder storeContact(Contact val) {
            storeContact = val;
            return this;
        }
        public Builder active(boolean val) {
            active = val;
            return this;
        }
        public Store build() {
            return new Store(this);
        }
    }

}
