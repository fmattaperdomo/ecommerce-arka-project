package com.fmattaperdomo.customer.service.domain.ports.output.message.publisher;

import com.fmattaperdomo.customer.service.domain.event.CustomerCreatedEvent;

public interface CustomerMessagePublisher {

    void publish(CustomerCreatedEvent customerCreatedEvent);

}
