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
                //revisar.set.setIdentification(customerCreatedEvent.getCustomer().getIdentification())
                .setFirstName(customerCreatedEvent.getCustomer().getFirstName())
                .setLastName(customerCreatedEvent.getCustomer().getLastName())
                //revisar.setEmail(customerCreatedEvent.getCustomer().getEmail())
                //revisar.setPhone(customerCreatedEvent.getCustomer().getPhone())
                //revisar.setRole(customerCreatedEvent.getCustomer().getRole())
                //revisar.setAddress(customerCreatedEvent.getCustomer().getAddress())
                .build();
    }
}

