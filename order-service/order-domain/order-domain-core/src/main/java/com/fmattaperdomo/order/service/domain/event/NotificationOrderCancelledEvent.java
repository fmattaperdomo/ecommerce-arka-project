package com.fmattaperdomo.order.service.domain.event;

import com.fmattaperdomo.order.service.domain.entity.Order;

import java.time.ZonedDateTime;

public class NotificationOrderCancelledEvent extends OrderEvent {
    public NotificationOrderCancelledEvent(Order order,
                                           ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}

