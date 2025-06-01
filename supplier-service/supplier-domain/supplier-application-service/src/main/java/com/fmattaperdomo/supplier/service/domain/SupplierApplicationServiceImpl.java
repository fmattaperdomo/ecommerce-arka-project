package com.fmattaperdomo.supplier.service.domain;

import com.fmattaperdomo.supplier.service.domain.create.CreateSupplierCommand;
import com.fmattaperdomo.supplier.service.domain.create.CreateSupplierResponse;
import com.fmattaperdomo.supplier.service.domain.event.SupplierCreatedEvent;
import com.fmattaperdomo.supplier.service.domain.mapper.SupplierDataMapper;
import com.fmattaperdomo.supplier.service.domain.ports.input.service.SupplierApplicationService;
import com.fmattaperdomo.supplier.service.domain.ports.output.message.publisher.SupplierMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
class SupplierApplicationServiceImpl implements SupplierApplicationService {

    private final SupplierCreateCommandHandler supplierCreateCommandHandler;

    private final SupplierDataMapper supplierDataMapper;

    private final SupplierMessagePublisher supplierMessagePublisher;

    public SupplierApplicationServiceImpl(SupplierCreateCommandHandler supplierCreateCommandHandler,
                                          SupplierDataMapper supplierDataMapper,
                                          SupplierMessagePublisher supplierMessagePublisher) {
        this.supplierCreateCommandHandler = supplierCreateCommandHandler;
        this.supplierDataMapper = supplierDataMapper;
        this.supplierMessagePublisher = supplierMessagePublisher;
    }

    @Override
    public CreateSupplierResponse createSupplier(CreateSupplierCommand createSupplierCommand) {
        SupplierCreatedEvent supplierCreatedEvent = supplierCreateCommandHandler.createSupplier(createSupplierCommand);
        supplierMessagePublisher.publish(supplierCreatedEvent);
        return supplierDataMapper
                .supplierToCreateSupplierResponse(supplierCreatedEvent.getSupplier(),
                        "Supplier saved successfully!");
    }
}
