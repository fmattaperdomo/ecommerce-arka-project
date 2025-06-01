package com.fmattaperdomo.supplier.service.domain.mapper;

import com.fmattaperdomo.supplier.service.domain.create.CreateSupplierCommand;
import com.fmattaperdomo.supplier.service.domain.create.CreateSupplierResponse;
import com.fmattaperdomo.supplier.service.domain.entity.Supplier;
import com.fmattaperdomo.domain.valueobject.SupplierId;
import org.springframework.stereotype.Component;

@Component
public class SupplierDataMapper {

    public Supplier createSupplierCommandToSupplier(CreateSupplierCommand createSupplierCommand) {
        return new Supplier(new SupplierId(createSupplierCommand.getSupplierId()),
                createSupplierCommand.getIdentification(),
                createSupplierCommand.getFirstName(),
                createSupplierCommand.getLastName(),
                createSupplierCommand.getEmail(),
                createSupplierCommand.getPhone(),
                createSupplierCommand.getRole(),
                createSupplierCommand.getAddress()
        );
    }

    public CreateSupplierResponse supplierToCreateSupplierResponse(Supplier supplier, String message) {
        return new CreateSupplierResponse(supplier.getId().getValue(), message);
    }
}
