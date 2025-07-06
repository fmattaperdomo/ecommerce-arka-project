package com.fmattaperdomo.store.service.domain.event;

import com.fmattaperdomo.domain.event.DomainEvent;
import com.fmattaperdomo.domain.valueobject.StoreId;
import com.fmattaperdomo.store.service.domain.entity.OrderApproval;

import java.time.ZonedDateTime;
import java.util.List;

public abstract class OrderApprovalEvent implements DomainEvent<OrderApproval> {
    private final OrderApproval orderApproval;
    private final StoreId storeId;
    private final List<String> failureMessages;
    private final ZonedDateTime createdAt;

    public OrderApprovalEvent(OrderApproval orderApproval,
                              StoreId storeId,
                              List<String> failureMessages,
                              ZonedDateTime createdAt) {
        this.orderApproval = orderApproval;
        this.storeId = storeId;
        this.failureMessages = failureMessages;
        this.createdAt = createdAt;
    }

    public OrderApproval getOrderApproval() {
        return orderApproval;
    }

    public StoreId getStoreId() {
        return storeId;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}

