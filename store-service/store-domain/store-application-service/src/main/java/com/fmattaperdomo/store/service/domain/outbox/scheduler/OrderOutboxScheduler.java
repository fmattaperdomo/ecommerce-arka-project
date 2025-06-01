package com.fmattaperdomo.store.service.domain.outbox.scheduler;

import com.fmattaperdomo.outbox.OutboxScheduler;
import com.fmattaperdomo.outbox.OutboxStatus;
import com.fmattaperdomo.store.service.domain.outbox.model.OrderOutboxMessage;
import com.fmattaperdomo.store.service.domain.ports.output.message.publisher.StoreApprovalResponseMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class OrderOutboxScheduler implements OutboxScheduler {

    private final OrderOutboxHelper orderOutboxHelper;
    private final StoreApprovalResponseMessagePublisher responseMessagePublisher;

    public OrderOutboxScheduler(OrderOutboxHelper orderOutboxHelper,
                                StoreApprovalResponseMessagePublisher responseMessagePublisher) {
        this.orderOutboxHelper = orderOutboxHelper;
        this.responseMessagePublisher = responseMessagePublisher;
    }

    @Transactional
    @Scheduled(fixedRateString = "${store-service.outbox-scheduler-fixed-rate}",
            initialDelayString = "${store-service.outbox-scheduler-initial-delay}")
    @Override
    public void processOutboxMessage() {
        Optional<List<OrderOutboxMessage>> outboxMessagesResponse =
                orderOutboxHelper.getOrderOutboxMessageByOutboxStatus(OutboxStatus.STARTED);
        if (outboxMessagesResponse.isPresent() && outboxMessagesResponse.get().size() > 0) {
            List<OrderOutboxMessage> outboxMessages = outboxMessagesResponse.get();
            log.info("Received {} OrderOutboxMessage with ids {}, sending to message bus!", outboxMessages.size(),
                    outboxMessages.stream().map(outboxMessage ->
                            outboxMessage.getId().toString()).collect(Collectors.joining(",")));
            outboxMessages.forEach(orderOutboxMessage ->
                    responseMessagePublisher.publish(orderOutboxMessage,
                            orderOutboxHelper::updateOutboxStatus));
            log.info("{} OrderOutboxMessage sent to message bus!", outboxMessages.size());
        }
    }



}
