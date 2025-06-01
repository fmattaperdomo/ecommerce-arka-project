package com.fmattaperdomo.supplier.service.domain;

import com.fmattaperdomo.supplier.service.domain.entity.Supplier;
import com.fmattaperdomo.supplier.service.domain.event.SupplierCreatedEvent;

public interface SupplierDomainService {

    SupplierCreatedEvent validateAndInitiateSupplier(Supplier supplier);

}

