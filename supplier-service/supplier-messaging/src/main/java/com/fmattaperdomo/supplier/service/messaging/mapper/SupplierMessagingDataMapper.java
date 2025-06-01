package com.fmattaperdomo.supplier.service.messaging.mapper;

import com.fmattaperdomo.supplier.service.domain.event.SupplierCreatedEvent;
import com.fmattaperdomo.kafka.order.avro.model.SupplierAvroModel;
import org.springframework.stereotype.Component;

@Component
public class SupplierMessagingDataMapper {

    public SupplierAvroModel paymentResponseAvroModelToPaymentResponse(SupplierCreatedEvent
                                                                               supplierCreatedEvent) {
        return SupplierAvroModel.newBuilder()
                .setId(supplierCreatedEvent.getSupplier().getId().getValue().toString())
                .setIdentification(supplierCreatedEvent.getSupplier().getIdentification())
                .setFirstName(supplierCreatedEvent.getSupplier().getFirstName())
                .setLastName(supplierCreatedEvent.getSupplier().getLastName())
                .setEmail(supplierCreatedEvent.getSupplier().getEmail())
                .setPhone(supplierCreatedEvent.getSupplier().getPhone())
                .setRole(supplierCreatedEvent.getSupplier().getRole())
                .setAddress(supplierCreatedEvent.getSupplier().getAddress())
                .build();
    }
}

