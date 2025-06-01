package com.fmattaperdomo.supplier.service.domain.ports.input.service;

import com.fmattaperdomo.supplier.service.domain.create.CreateSupplierCommand;
import com.fmattaperdomo.supplier.service.domain.create.CreateSupplierResponse;
import jakarta.validation.Valid;

public interface SupplierApplicationService {

    CreateSupplierResponse createSupplier(@Valid CreateSupplierCommand createSupplierCommand);

}
