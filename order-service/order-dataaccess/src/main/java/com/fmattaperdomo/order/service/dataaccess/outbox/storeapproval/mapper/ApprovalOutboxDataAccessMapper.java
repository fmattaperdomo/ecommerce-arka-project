package com.fmattaperdomo.order.service.dataaccess.outbox.storeapproval.mapper;

import com.fmattaperdomo.order.service.dataaccess.outbox.storeapproval.entity.ApprovalOutboxEntity;
import com.fmattaperdomo.order.service.domain.outbox.model.approval.OrderApprovalOutboxMessage;
import org.springframework.stereotype.Component;

@Component
public class ApprovalOutboxDataAccessMapper {

    public ApprovalOutboxEntity orderCreatedOutboxMessageToOutboxEntity(OrderApprovalOutboxMessage
                                                                                orderApprovalOutboxMessage) {
        return ApprovalOutboxEntity.builder()
                .id(orderApprovalOutboxMessage.getId())
                .sagaId(orderApprovalOutboxMessage.getSagaId())
                .createdAt(orderApprovalOutboxMessage.getCreatedAt())
                .type(orderApprovalOutboxMessage.getType())
                .payload(orderApprovalOutboxMessage.getPayload())
                .orderStatus(orderApprovalOutboxMessage.getOrderStatus())
                .sagaStatus(orderApprovalOutboxMessage.getSagaStatus())
                .outboxStatus(orderApprovalOutboxMessage.getOutboxStatus())
                .version(orderApprovalOutboxMessage.getVersion())
                .build();
    }

    public OrderApprovalOutboxMessage approvalOutboxEntityToOrderApprovalOutboxMessage(ApprovalOutboxEntity
                                                                                               approvalOutboxEntity) {
        return OrderApprovalOutboxMessage.builder()
                .id(approvalOutboxEntity.getId())
                .sagaId(approvalOutboxEntity.getSagaId())
                .createdAt(approvalOutboxEntity.getCreatedAt())
                .type(approvalOutboxEntity.getType())
                .payload(approvalOutboxEntity.getPayload())
                .orderStatus(approvalOutboxEntity.getOrderStatus())
                .sagaStatus(approvalOutboxEntity.getSagaStatus())
                .outboxStatus(approvalOutboxEntity.getOutboxStatus())
                .version(approvalOutboxEntity.getVersion())
                .build();
    }

}
