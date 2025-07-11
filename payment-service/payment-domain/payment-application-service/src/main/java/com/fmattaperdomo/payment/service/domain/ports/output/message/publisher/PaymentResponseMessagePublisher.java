package com.fmattaperdomo.payment.service.domain.ports.output.message.publisher;

import com.fmattaperdomo.outbox.OutboxStatus;
import com.fmattaperdomo.payment.service.domain.outbox.model.OrderOutboxMessage;

import java.util.function.BiConsumer;

public interface PaymentResponseMessagePublisher {
    void publish(OrderOutboxMessage orderOutboxMessage,
                 BiConsumer<OrderOutboxMessage, OutboxStatus> outboxCallback);
}

