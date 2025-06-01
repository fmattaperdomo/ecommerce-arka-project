package com.fmattaperdomo.order.service.domain.entity;

import com.fmattaperdomo.domain.entity.AggregateRoot;
import com.fmattaperdomo.domain.valueobject.*;

import java.time.ZonedDateTime;

public class Notification extends AggregateRoot<NotificationId> {
    private Order order;
    private ZonedDateTime createdAt;

    public Notification(Order order) {
        this.order = order;
        createdAt = ZonedDateTime.now();
    }

    public Notification(NotificationId notificationId) {
        super.setId(notificationId);
    }

    public Order getOrder() {
        return order;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
