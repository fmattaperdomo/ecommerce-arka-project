package com.fmattaperdomo.store.service.domain;

import com.fmattaperdomo.domain.valueobject.OrderApprovalStatus;
import com.fmattaperdomo.store.service.domain.entity.Store;
import com.fmattaperdomo.store.service.domain.event.OrderApprovalEvent;
import com.fmattaperdomo.store.service.domain.event.OrderApprovedEvent;
import com.fmattaperdomo.store.service.domain.event.OrderRejectedEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static com.fmattaperdomo.domain.DomainConstants.UTC;

@Slf4j
public class StoreDomainServiceImpl implements StoreDomainService {
    @Override
    public OrderApprovalEvent validateOrder(Store store, List<String> failureMessages) {
        store.validateOrder(failureMessages);
        log.info("Validating order with id: {}", store.getOrderDetail().getId().getValue());

        if (failureMessages.isEmpty()) {
            log.info("Order is approved for order id: {}", store.getOrderDetail().getId().getValue());
            store.constructOrderApproval(OrderApprovalStatus.APPROVED);
            return new OrderApprovedEvent(store.getOrderApproval(),
                    store.getId(),
                    failureMessages,
                    ZonedDateTime.now(ZoneId.of(UTC)));
        } else {
            log.info("Order is rejected for order id: {}", store.getOrderDetail().getId().getValue());
            store.constructOrderApproval(OrderApprovalStatus.REJECTED);
            return new OrderRejectedEvent(store.getOrderApproval(),
                    store.getId(),
                    failureMessages,
                    ZonedDateTime.now(ZoneId.of(UTC)));
        }
    }
}
