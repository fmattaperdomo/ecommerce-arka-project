package com.fmattaperdomo.supplier.service.dataaccess.supplier.adapter;

import com.fmattaperdomo.supplier.service.dataaccess.supplier.mapper.SupplierDataAccessMapper;
import com.fmattaperdomo.supplier.service.dataaccess.supplier.repository.SupplierJpaRepository;
import com.fmattaperdomo.supplier.service.domain.entity.Supplier;
import com.fmattaperdomo.supplier.service.domain.ports.output.repository.SupplierRepository;
import org.springframework.stereotype.Component;

@Component
public class SupplierRepositoryImpl implements SupplierRepository {

    private final SupplierJpaRepository supplierJpaRepository;

    private final SupplierDataAccessMapper supplierDataAccessMapper;

    public SupplierRepositoryImpl(SupplierJpaRepository supplierJpaRepository,
                                  SupplierDataAccessMapper supplierDataAccessMapper) {
        this.supplierJpaRepository = supplierJpaRepository;
        this.supplierDataAccessMapper = supplierDataAccessMapper;
    }

    @Override
    public Supplier createSupplier(Supplier supplier) {
        return supplierDataAccessMapper.supplierEntityToSupplier(
                supplierJpaRepository.save(supplierDataAccessMapper.supplierToSupplierEntity(supplier)));
    }
}
