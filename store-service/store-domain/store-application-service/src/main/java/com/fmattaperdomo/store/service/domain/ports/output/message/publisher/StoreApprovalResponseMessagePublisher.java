package com.fmattaperdomo.store.service.domain.ports.output.message.publisher;


import com.fmattaperdomo.outbox.OutboxStatus;
import com.fmattaperdomo.store.service.domain.outbox.model.OrderOutboxMessage;

import java.util.function.BiConsumer;

public interface StoreApprovalResponseMessagePublisher {

    void publish(OrderOutboxMessage orderOutboxMessage,
                 BiConsumer<OrderOutboxMessage, OutboxStatus> outboxCallback);
}
