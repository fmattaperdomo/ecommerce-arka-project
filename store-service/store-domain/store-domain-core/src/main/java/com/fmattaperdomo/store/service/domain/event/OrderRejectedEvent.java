package com.fmattaperdomo.store.service.domain.event;

import com.fmattaperdomo.domain.valueobject.StoreId;
import com.fmattaperdomo.store.service.domain.entity.OrderApproval;

import java.time.ZonedDateTime;
import java.util.List;

public class OrderRejectedEvent extends OrderApprovalEvent {

    public OrderRejectedEvent(OrderApproval orderApproval,
                              StoreId storeId,
                              List<String> failureMessages,
                              ZonedDateTime createdAt) {
        super(orderApproval, storeId, failureMessages, createdAt);
    }

}
