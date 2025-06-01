package com.fmattaperdomo.supplier.service.domain;

import com.fmattaperdomo.supplier.service.domain.create.CreateSupplierCommand;
import com.fmattaperdomo.supplier.service.domain.entity.Supplier;
import com.fmattaperdomo.supplier.service.domain.event.SupplierCreatedEvent;
import com.fmattaperdomo.supplier.service.domain.exception.SupplierDomainException;
import com.fmattaperdomo.supplier.service.domain.mapper.SupplierDataMapper;
import com.fmattaperdomo.supplier.service.domain.ports.output.repository.SupplierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
class SupplierCreateCommandHandler {

    private final SupplierDomainService supplierDomainService;

    private final SupplierRepository supplierRepository;

    private final SupplierDataMapper supplierDataMapper;

    public SupplierCreateCommandHandler(SupplierDomainService supplierDomainService,
                                        SupplierRepository supplierRepository,
                                        SupplierDataMapper supplierDataMapper) {
        this.supplierDomainService = supplierDomainService;
        this.supplierRepository = supplierRepository;
        this.supplierDataMapper = supplierDataMapper;
    }

    @Transactional
    public SupplierCreatedEvent createSupplier(CreateSupplierCommand createSupplierCommand) {
        Supplier supplier = supplierDataMapper.createSupplierCommandToSupplier(createSupplierCommand);
        SupplierCreatedEvent supplierCreatedEvent = supplierDomainService.validateAndInitiateSupplier(supplier);
        Supplier savedSupplier = supplierRepository.createSupplier(supplier);
        if (savedSupplier == null) {
            log.error("Could not save supplier with id: {}", createSupplierCommand.getSupplierId());
            throw new SupplierDomainException("Could not save supplier with id " +
                    createSupplierCommand.getSupplierId());
        }
        log.info("Returning SupplierCreatedEvent for supplier id: {}", createSupplierCommand.getSupplierId());
        return supplierCreatedEvent;
    }
}
