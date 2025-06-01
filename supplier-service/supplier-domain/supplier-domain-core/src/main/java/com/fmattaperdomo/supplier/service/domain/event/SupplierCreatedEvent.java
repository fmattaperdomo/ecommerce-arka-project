package com.fmattaperdomo.supplier.service.domain.event;

import com.fmattaperdomo.supplier.service.domain.entity.Supplier;
import com.fmattaperdomo.domain.event.DomainEvent;

import java.time.ZonedDateTime;

public class SupplierCreatedEvent implements DomainEvent<Supplier> {

    private final Supplier supplier;

    private final ZonedDateTime createdAt;

    public SupplierCreatedEvent(Supplier supplier, ZonedDateTime createdAt) {
        this.supplier = supplier;
        this.createdAt = createdAt;
    }

    public Supplier getSupplier() {
        return supplier;
    }
}