package com.fmattaperdomo.store.service.messaging.mapper;

import com.fmattaperdomo.domain.valueobject.ProductId;
import com.fmattaperdomo.domain.valueobject.ProductStoreId;
import com.fmattaperdomo.domain.valueobject.StoreOrderStatus;
import com.fmattaperdomo.kafka.order.avro.model.OrderApprovalStatus;
import com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel;
import com.fmattaperdomo.kafka.order.avro.model.StoreApprovalResponseAvroModel;
import com.fmattaperdomo.store.service.domain.dto.StoreApprovalRequest;
import com.fmattaperdomo.store.service.domain.entity.ProductStore;
import com.fmattaperdomo.store.service.domain.event.OrderApprovedEvent;
import com.fmattaperdomo.store.service.domain.event.OrderRejectedEvent;
import com.fmattaperdomo.store.service.domain.outbox.model.OrderEventPayload;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class StoreMessagingDataMapper {

    public StoreApprovalRequest
    storeApprovalRequestAvroModelToStoreApproval(StoreApprovalRequestAvroModel
                                                                   storeApprovalRequestAvroModel) {
        return StoreApprovalRequest.builder()
                .id(storeApprovalRequestAvroModel.getId())
                .sagaId(storeApprovalRequestAvroModel.getSagaId())
                .storeId(storeApprovalRequestAvroModel.getStoreId())
                .orderId(storeApprovalRequestAvroModel.getOrderId())
                .storeOrderStatus(StoreOrderStatus.valueOf(storeApprovalRequestAvroModel
                        .getStoreOrderStatus().name()))
                .productsStore(storeApprovalRequestAvroModel.getProductsStore()
                        .stream().map(avroModel ->
                                ProductStore.builder()
                                        .productStoreId(new ProductStoreId(UUID.fromString(avroModel.getId())))
                                        .quantity(avroModel.getStockQuantity())
                                        .build())
                        .collect(Collectors.toList()))
                .createdAt(storeApprovalRequestAvroModel.getCreatedAt())
                .build();
    }

    public StoreApprovalResponseAvroModel
    orderEventPayloadToStoreApprovalResponseAvroModel(String sagaId, OrderEventPayload orderEventPayload) {
        return StoreApprovalResponseAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId(sagaId)
                .setOrderId(orderEventPayload.getOrderId())
                .setStoreId(orderEventPayload.getStoreId())
                .setCreatedAt(orderEventPayload.getCreatedAt().toInstant())
                .setOrderApprovalStatus(OrderApprovalStatus.valueOf(orderEventPayload.getOrderApprovalStatus()))
                .setFailureMessages(orderEventPayload.getFailureMessages())
                .build();
    }
}

