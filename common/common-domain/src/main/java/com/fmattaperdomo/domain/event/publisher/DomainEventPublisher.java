package com.fmattaperdomo.domain.event.publisher;

import com.fmattaperdomo.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(T domainEvent);
}

