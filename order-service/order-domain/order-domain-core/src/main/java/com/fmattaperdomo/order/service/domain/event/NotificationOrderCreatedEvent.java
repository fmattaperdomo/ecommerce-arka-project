package com.fmattaperdomo.order.service.domain.event;

import com.fmattaperdomo.order.service.domain.entity.Order;

import java.time.ZonedDateTime;

public class NotificationOrderCreatedEvent extends OrderEvent {
    public NotificationOrderCreatedEvent(Order order,
                                         ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}

