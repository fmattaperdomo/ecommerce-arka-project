package com.fmattaperdomo.order.service.domain.ports.output.message.publisher.payment;

import com.fmattaperdomo.order.service.domain.outbox.model.payment.OrderPaymentOutboxMessage;
import com.fmattaperdomo.outbox.OutboxStatus;

import java.util.function.BiConsumer;

public interface PaymentRequestMessagePublisher {

    void publish(OrderPaymentOutboxMessage orderPaymentOutboxMessage,
                 BiConsumer<OrderPaymentOutboxMessage, OutboxStatus> outboxCallback);
}
