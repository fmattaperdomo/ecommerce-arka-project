package com.fmattaperdomo.store.service.domain;

import com.fmattaperdomo.domain.valueobject.OrderId;
import com.fmattaperdomo.outbox.OutboxStatus;
import com.fmattaperdomo.store.service.domain.dto.StoreApprovalRequest;
import com.fmattaperdomo.store.service.domain.entity.Store;
import com.fmattaperdomo.store.service.domain.event.OrderApprovalEvent;
import com.fmattaperdomo.store.service.domain.exception.StoreNotFoundException;
import com.fmattaperdomo.store.service.domain.mapper.StoreDataMapper;
import com.fmattaperdomo.store.service.domain.outbox.model.OrderOutboxMessage;
import com.fmattaperdomo.store.service.domain.outbox.scheduler.OrderOutboxHelper;
import com.fmattaperdomo.store.service.domain.ports.output.message.publisher.StoreApprovalResponseMessagePublisher;
import com.fmattaperdomo.store.service.domain.ports.output.repository.OrderApprovalRepository;
import com.fmattaperdomo.store.service.domain.ports.output.repository.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class StoreApprovalRequestHelper {

    private final StoreDomainService storeDomainService;
    private final StoreDataMapper storeDataMapper;
    private final StoreRepository storeRepository;
    private final OrderApprovalRepository orderApprovalRepository;
    private final OrderOutboxHelper orderOutboxHelper;
    private final StoreApprovalResponseMessagePublisher storeApprovalResponseMessagePublisher;



    public StoreApprovalRequestHelper(StoreDomainService storeDomainService,
                                      StoreDataMapper storeDataMapper,
                                      StoreRepository storeRepository,
                                      OrderApprovalRepository orderApprovalRepository,
                                      OrderOutboxHelper orderOutboxHelper,
                                      StoreApprovalResponseMessagePublisher
                                                   storeApprovalResponseMessagePublisher) {
        this.storeDomainService = storeDomainService;
        this.storeDataMapper = storeDataMapper;
        this.storeRepository = storeRepository;
        this.orderApprovalRepository = orderApprovalRepository;
        this.orderOutboxHelper = orderOutboxHelper;
        this.storeApprovalResponseMessagePublisher = storeApprovalResponseMessagePublisher;
    }

    @Transactional
    public void persistOrderApproval(StoreApprovalRequest storeApprovalRequest) {
        if (publishIfOutboxMessageProcessed(storeApprovalRequest)) {
            log.info("An outbox message with saga id: {} already saved to database!",
                    storeApprovalRequest.getSagaId());
            return;
        }

        log.info("Processing store approval for order id: {}", storeApprovalRequest.getOrderId());
        List<String> failureMessages = new ArrayList<>();
        Store store = findStore(storeApprovalRequest);
        OrderApprovalEvent orderApprovalEvent =
                storeDomainService.validateOrder(
                        store,
                        failureMessages);
        orderApprovalRepository.save(store.getOrderApproval());

        orderOutboxHelper
                .saveOrderOutboxMessage(storeDataMapper.orderApprovalEventToOrderEventPayload(orderApprovalEvent),
                        orderApprovalEvent.getOrderApproval().getApprovalStatus(),
                        OutboxStatus.STARTED,
                        UUID.fromString(storeApprovalRequest.getSagaId()));

    }

    private Store findStore(StoreApprovalRequest storeApprovalRequest) {
        Store store = storeDataMapper
                .storeApprovalRequestToStore(storeApprovalRequest);
        Optional<Store> storeResult = storeRepository.findStoreInformation(store);
        if (storeResult.isEmpty()) {
            log.error("Store with id " + store.getId().getValue() + " not found!");
            throw new StoreNotFoundException("Store with id " + store.getId().getValue() +
                    " not found!");
        }

        Store storeEntity = storeResult.get();
        store.setActive(storeEntity.isActive());
        store.getOrderDetail().getProductsStore().forEach(productStore ->
                storeEntity.getOrderDetail().getProductsStore().forEach(p -> {
                    if (p.getId().equals(productStore.getId())) {
                        productStore.updateWithConfirmedNamePriceAndAvailability(p.getBarCode(), p.getPrice(), p.isAvailable());
                    }
                }));
        store.getOrderDetail().setId(new OrderId(UUID.fromString(storeApprovalRequest.getOrderId())));

        return store;
    }

    private boolean publishIfOutboxMessageProcessed(StoreApprovalRequest storeApprovalRequest) {
        Optional<OrderOutboxMessage> orderOutboxMessage =
                orderOutboxHelper.getCompletedOrderOutboxMessageBySagaIdAndOutboxStatus(UUID
                        .fromString(storeApprovalRequest.getSagaId()), OutboxStatus.COMPLETED);
        if (orderOutboxMessage.isPresent()) {
            storeApprovalResponseMessagePublisher.publish(orderOutboxMessage.get(),
                    orderOutboxHelper::updateOutboxStatus);
            return true;
        }
        return false;
    }
}
