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
                .setIdentification(customerCreatedEvent.getCustomer().getIdentification())
                .setFirstName(customerCreatedEvent.getCustomer().getFirstName())
                .setLastName(customerCreatedEvent.getCustomer().getLastName())
                .setEmail(customerCreatedEvent.getCustomer().getEmail())
                .setPhone(customerCreatedEvent.getCustomer().getPhone())
                .setRole(customerCreatedEvent.getCustomer().getRole())
                .setAddress(customerCreatedEvent.getCustomer().getAddress())
                .build();
    }
}

