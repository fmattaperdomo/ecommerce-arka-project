package com.fmattaperdomo.order.service.domain;

import com.fmattaperdomo.order.service.domain.dto.message.StoreApprovalResponse;
import com.fmattaperdomo.order.service.domain.event.OrderCancelledEvent;
import com.fmattaperdomo.order.service.domain.ports.input.message.listener.storeapproval.StoreApprovalResponseMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static com.fmattaperdomo.order.service.domain.entity.Order.FAILURE_MESSAGE_DELIMITER;

@Slf4j
@Validated
@Service
public class StoreApprovalResponseMessageListenerImpl implements StoreApprovalResponseMessageListener {

    private final OrderApprovalSaga orderApprovalSaga;

    public StoreApprovalResponseMessageListenerImpl(OrderApprovalSaga orderApprovalSaga) {
        this.orderApprovalSaga = orderApprovalSaga;
    }

    @Override
    public void orderApproved(StoreApprovalResponse storeApprovalResponse) {
        orderApprovalSaga.process(storeApprovalResponse);
        log.info("Order is approved for order id: {}", storeApprovalResponse.getOrderId());
    }

    @Override
    public void orderRejected(StoreApprovalResponse storeApprovalResponse) {
        orderApprovalSaga.rollback(storeApprovalResponse);
        log.info("Order Approval Saga rollback operation is completed for order id: {} with failure messages: {}",
                storeApprovalResponse.getOrderId(),
                String.join(FAILURE_MESSAGE_DELIMITER, storeApprovalResponse.getFailureMessages()));
    }
}

