package com.fmattaperdomo.customer.service.messaging.mapper;

import com.fmattaperdomo.customer.service.domain.event.CustomerCreatedEvent;
import com.fmattaperdomo.kafka.order.avro.model.CustomerAvroModel;
import org.springframework.stereotype.Component;

@Component
public class CustomerMessagingDataMapper {

    public CustomerAvroModel paymentResponseAvroModelToPaymentResponse(CustomerCreatedEvent
                                                                               customerCreatedEvent) {
        return CustomerAvroModel.newBuilder()
                .setId(customerCreatedEvent.getCustomer().getId().getValue().toString())
                .setTypeIdentification(customerCreatedEvent.getCustomer().getTypeIdentification().name())
                .setDocumentNumber(customerCreatedEvent.getCustomer().getDocumentNumber())
                .setFirstName(customerCreatedEvent.getCustomer().getFirstName())
                .setLastName(customerCreatedEvent.getCustomer().getLastName())
                .setEmail(customerCreatedEvent.getCustomer().getEmail())
                .setPhone(customerCreatedEvent.getCustomer().getPhone())
                .setUserRole(customerCreatedEvent.getCustomer().getUserRole().name())
                .build();
    }
}

