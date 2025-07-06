package com.fmattaperdomo.order.service.domain.ports.output.message.publisher.storeapproval;

import com.fmattaperdomo.order.service.domain.outbox.model.approval.OrderApprovalOutboxMessage;
import com.fmattaperdomo.outbox.OutboxStatus;

import java.util.function.BiConsumer;

public interface StoreApprovalRequestMessagePublisher {

    void publish(OrderApprovalOutboxMessage orderApprovalOutboxMessage,
                 BiConsumer<OrderApprovalOutboxMessage, OutboxStatus> outboxCallback);
}
