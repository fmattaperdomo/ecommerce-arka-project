package com.fmattaperdomo.supplier.service.domain.ports.output.repository;

import com.fmattaperdomo.supplier.service.domain.entity.Supplier;

public interface SupplierRepository {

    Supplier createSupplier(Supplier supplier);
}

