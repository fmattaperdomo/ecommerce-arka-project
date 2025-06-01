package com.fmattaperdomo.supplier.service.domain.ports.output.message.publisher;

import com.fmattaperdomo.supplier.service.domain.event.SupplierCreatedEvent;

public interface SupplierMessagePublisher {

    void publish(SupplierCreatedEvent supplierCreatedEvent);

}
