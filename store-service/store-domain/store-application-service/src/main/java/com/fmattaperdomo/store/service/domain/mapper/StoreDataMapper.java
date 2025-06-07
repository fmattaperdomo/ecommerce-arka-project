package com.fmattaperdomo.store.service.domain.mapper;

import com.fmattaperdomo.domain.valueobject.Money;
import com.fmattaperdomo.domain.valueobject.OrderId;
import com.fmattaperdomo.domain.valueobject.OrderStatus;
import com.fmattaperdomo.domain.valueobject.StoreId;
import com.fmattaperdomo.store.service.domain.dto.StoreApprovalRequest;
import com.fmattaperdomo.store.service.domain.entity.OrderDetail;
import com.fmattaperdomo.store.service.domain.entity.Product;
import com.fmattaperdomo.store.service.domain.entity.ProductStore;
import com.fmattaperdomo.store.service.domain.entity.Store;
import com.fmattaperdomo.store.service.domain.event.OrderApprovalEvent;
import com.fmattaperdomo.store.service.domain.outbox.model.OrderEventPayload;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class StoreDataMapper {
    public Store storeApprovalRequestToStore(StoreApprovalRequest
                                                                    storeApprovalRequest) {
        return Store.builder()
                .storeId(new StoreId(UUID.fromString(storeApprovalRequest.getStoreId())))
                .orderDetail(OrderDetail.builder()
                        .orderId(new OrderId(UUID.fromString(storeApprovalRequest.getOrderId())))
                        .productsStore(storeApprovalRequest.getProductsStore().stream().map(
                                        productStore -> ProductStore.builder()
                                                .productStoreId(productStore.getId())
                                                .quantity(productStore.getQuantity())
                                                .build())
                                .collect(Collectors.toList()))
                        .totalAmount(new Money(storeApprovalRequest.getPrice()))
                        .orderStatus(OrderStatus.valueOf(storeApprovalRequest.getStoreOrderStatus().name()))
                        .build())
                .build();
    }

    public OrderEventPayload
    orderApprovalEventToOrderEventPayload(OrderApprovalEvent orderApprovalEvent) {
        return OrderEventPayload.builder()
                .orderId(orderApprovalEvent.getOrderApproval().getOrderId().getValue().toString())
                .storeId(orderApprovalEvent.getStoreId().getValue().toString())
                .orderApprovalStatus(orderApprovalEvent.getOrderApproval().getApprovalStatus().name())
                .createdAt(orderApprovalEvent.getCreatedAt())
                .failureMessages(orderApprovalEvent.getFailureMessages())
                .build();
    }
}

