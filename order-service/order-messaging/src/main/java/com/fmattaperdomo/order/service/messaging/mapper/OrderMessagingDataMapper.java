package com.fmattaperdomo.order.service.messaging.mapper;

import com.fmattaperdomo.kafka.order.avro.model.*;
import com.fmattaperdomo.order.service.domain.dto.message.CustomerModel;
import com.fmattaperdomo.order.service.domain.dto.message.PaymentResponse;
import com.fmattaperdomo.order.service.domain.dto.message.StoreApprovalResponse;
import com.fmattaperdomo.order.service.domain.outbox.model.approval.OrderApprovalEventPayload;
import com.fmattaperdomo.order.service.domain.outbox.model.payment.OrderPaymentEventPayload;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderMessagingDataMapper {

    public PaymentResponse paymentResponseAvroModelToPaymentResponse(PaymentResponseAvroModel
                                                                             paymentResponseAvroModel) {
        return PaymentResponse.builder()
                .id(paymentResponseAvroModel.getId())
                .sagaId(paymentResponseAvroModel.getSagaId())
                .paymentId(paymentResponseAvroModel.getPaymentId())
                .customerId(paymentResponseAvroModel.getCustomerId())
                .orderId(paymentResponseAvroModel.getOrderId())
                .price(paymentResponseAvroModel.getPrice())
                .createdAt(paymentResponseAvroModel.getCreatedAt())
                .paymentStatus(com.fmattaperdomo.domain.valueobject.PaymentStatus.valueOf(
                        paymentResponseAvroModel.getPaymentStatus().name()))
                .failureMessages(paymentResponseAvroModel.getFailureMessages())
                .build();
    }

    public StoreApprovalResponse
    approvalResponseAvroModelToApprovalResponse(StoreApprovalResponseAvroModel
                                                        storeApprovalResponseAvroModel) {
        return StoreApprovalResponse.builder()
                .id(storeApprovalResponseAvroModel.getId())
                .sagaId(storeApprovalResponseAvroModel.getSagaId())
                .storeId(storeApprovalResponseAvroModel.getStoreId())
                .orderId(storeApprovalResponseAvroModel.getOrderId())
                .createdAt(storeApprovalResponseAvroModel.getCreatedAt())
                .orderApprovalStatus(com.fmattaperdomo.domain.valueobject.OrderApprovalStatus.valueOf(
                        storeApprovalResponseAvroModel.getOrderApprovalStatus().name()))
                .failureMessages(storeApprovalResponseAvroModel.getFailureMessages())
                .build();
    }

    public PaymentRequestAvroModel orderPaymentEventToPaymentRequestAvroModel(String sagaId, OrderPaymentEventPayload
            orderPaymentEventPayload) {
        return PaymentRequestAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId(sagaId)
                .setCustomerId(orderPaymentEventPayload.getCustomerId())
                .setOrderId(orderPaymentEventPayload.getOrderId())
                .setPrice(orderPaymentEventPayload.getPrice())
                .setCreatedAt(orderPaymentEventPayload.getCreatedAt().toInstant())
                .setPaymentOrderStatus(PaymentOrderStatus.valueOf(orderPaymentEventPayload.getPaymentOrderStatus()))
                .build();
    }

    public StoreApprovalRequestAvroModel
    orderApprovalEventToStoreApprovalRequestAvroModel(String sagaId, OrderApprovalEventPayload
            orderApprovalEventPayload) {
        return StoreApprovalRequestAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId(sagaId)
                .setOrderId(orderApprovalEventPayload.getOrderId())
                .setStoreId(orderApprovalEventPayload.getStoreId())
                .setStoreOrderStatus(StoreOrderStatus
                        .valueOf(orderApprovalEventPayload.getStoreOrderStatus()))
                .setProductsStore(orderApprovalEventPayload.getProductsStore().stream().map(orderApprovalEventProduct ->
                        com.fmattaperdomo.kafka.order.avro.model.ProductStore.newBuilder()
                                .setId(orderApprovalEventProductStore.getId())
                                .setQuantity(orderApprovalEventProduct.getQuantity())
                                .build()).collect(Collectors.toList()))
                .setPrice(orderApprovalEventPayload.getPrice())
                .setCreatedAt(orderApprovalEventPayload.getCreatedAt().toInstant())
                .build();
    }

    public CustomerModel customerAvroModeltoCustomerModel(CustomerAvroModel customerAvroModel) {
        return CustomerModel.builder()
                .id(customerAvroModel.getId())
                .username(customerAvroModel.getUsername())
                .firstName(customerAvroModel.getFirstName())
                .lastName(customerAvroModel.getLastName())
                .build();
    }
}

