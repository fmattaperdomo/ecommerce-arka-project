package com.fmattaperdomo.supplier.service.dataaccess.supplier.mapper;

import com.fmattaperdomo.supplier.service.dataaccess.supplier.entity.SupplierEntity;
import com.fmattaperdomo.supplier.service.domain.entity.Supplier;
import com.fmattaperdomo.domain.valueobject.SupplierId;
import org.springframework.stereotype.Component;

@Component
public class SupplierDataAccessMapper {
    public Supplier supplierEntityToSupplier(SupplierEntity supplierEntity) {
        return new Supplier(new SupplierId(supplierEntity.getId()),
                supplierEntity.getIdentification(),
                supplierEntity.getFirstName(),
                supplierEntity.getLastName(),
                supplierEntity.getEmail(),
                supplierEntity.getPhone(),
                supplierEntity.getRole(),
                supplierEntity.getAddress()
        );
    }

    public SupplierEntity supplierToSupplierEntity(Supplier supplier) {
        return SupplierEntity.builder()
                .id(supplier.getId().getValue())
                .identification(supplier.getIdentification())
                .firstName(supplier.getFirstName())
                .lastName(supplier.getLastName())
                .email(supplier.getEmail())
                .phone(supplier.getPhone())
                .role(supplier.getRole())
                .address(supplier.getAddress())
                .build();
    }
}
