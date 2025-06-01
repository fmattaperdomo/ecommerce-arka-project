package com.fmattaperdomo.supplier.service.domain;

import com.fmattaperdomo.supplier.service.domain.entity.Supplier;
import com.fmattaperdomo.supplier.service.domain.event.SupplierCreatedEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
public class SupplierDomainServiceImpl implements SupplierDomainService {

    public SupplierCreatedEvent validateAndInitiateSupplier(Supplier supplier) {
        //Any Business logic required to run for a supplier creation
        log.info("Supplier with id: {} is initiated", supplier.getId().getValue());
        return new SupplierCreatedEvent(supplier, ZonedDateTime.now(ZoneId.of("UTC")));
    }
}

