package com.fmattaperdomo.order.service.domain.mapper;

import com.fmattaperdomo.domain.valueobject.*;
import com.fmattaperdomo.order.service.domain.dto.create.CreateOrderCommand;
import com.fmattaperdomo.order.service.domain.dto.create.CreateOrderResponse;
import com.fmattaperdomo.order.service.domain.dto.create.OrderAddress;
import com.fmattaperdomo.order.service.domain.dto.message.CustomerModel;
import com.fmattaperdomo.order.service.domain.dto.track.TrackOrderResponse;
import com.fmattaperdomo.order.service.domain.entity.*;
import com.fmattaperdomo.order.service.domain.event.OrderCancelledEvent;
import com.fmattaperdomo.order.service.domain.event.OrderCreatedEvent;
import com.fmattaperdomo.order.service.domain.event.OrderPaidEvent;
import com.fmattaperdomo.order.service.domain.outbox.model.approval.OrderApprovalEventPayload;
import com.fmattaperdomo.order.service.domain.outbox.model.approval.OrderApprovalEventProduct;
import com.fmattaperdomo.order.service.domain.outbox.model.payment.OrderPaymentEventPayload;


import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDataMapper {

    public Store createOrderCommandToStore(CreateOrderCommand createOrderCommand) {
        return Store.builder()
                .storeId(new StoreId(createOrderCommand.getStoreId()))
                .productsStore(createOrderCommand.getItems().stream().map(orderItem ->
                                new ProductStore(new ProductStoreId(orderItem.getProductStoreId())))
                        .collect(Collectors.toList()))
                .build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return Order.builder()
                .customerId(new CustomerId(createOrderCommand.getCustomerId()))
                .storeId(new StoreId(createOrderCommand.getStoreId()))
                .deliveryAddress(orderAddressToAddress(createOrderCommand.getAddress()))
                .totalAmount(new Money(createOrderCommand.getTotalAmount()))
                .items(orderItemsToOrderItemEntities(createOrderCommand.getItems()))
                .build();
    }

    public CreateOrderResponse orderToCreateOrderResponse(Order order, String message) {
        return CreateOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .message(message)
                .build();
    }

    public TrackOrderResponse orderToTrackOrderResponse(Order order) {
        return TrackOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .failureMessages(order.getFailureMessages())
                .build();
    }

    public OrderPaymentEventPayload orderCreatedEventToOrderPaymentEventPayload(OrderCreatedEvent orderCreatedEvent) {
        return OrderPaymentEventPayload.builder()
                .customerId(orderCreatedEvent.getOrder().getCustomerId().getValue().toString())
                .orderId(orderCreatedEvent.getOrder().getId().getValue().toString())
                .price(orderCreatedEvent.getOrder().getTotalAmount().getAmount())
                .createdAt(orderCreatedEvent.getCreatedAt())
                .paymentOrderStatus(PaymentOrderStatus.PENDING.name())
                .build();
    }

    public OrderPaymentEventPayload orderCancelledEventToOrderPaymentEventPayload(OrderCancelledEvent
                                                                                          orderCancelledEvent) {
        return OrderPaymentEventPayload.builder()
                .customerId(orderCancelledEvent.getOrder().getCustomerId().getValue().toString())
                .orderId(orderCancelledEvent.getOrder().getId().getValue().toString())
                .price(orderCancelledEvent.getOrder().getTotalAmount().getAmount())
                .createdAt(orderCancelledEvent.getCreatedAt())
                .paymentOrderStatus(PaymentOrderStatus.CANCELLED.name())
                .build();
    }

    public OrderApprovalEventPayload orderPaidEventToOrderApprovalEventPayload(OrderPaidEvent orderPaidEvent) {
        return OrderApprovalEventPayload.builder()
                .orderId(orderPaidEvent.getOrder().getId().getValue().toString())
                .storeId(orderPaidEvent.getOrder().getStoreId().getValue().toString())
                .storeOrderStatus(StoreOrderStatus.PAID.name())
                .productsStore(orderPaidEvent.getOrder().getItems().stream().map(orderItem ->
                        OrderApprovalEventProduct.builder()
                                .id(orderItem.getProductStore().getId().getValue().toString())
                                .quantity(orderItem.getQuantity())
                                .build()).collect(Collectors.toList()))
                .price(orderPaidEvent.getOrder().getTotalAmount().getAmount())
                .createdAt(orderPaidEvent.getCreatedAt())
                .build();
    }

    public Customer customerModelToCustomer(CustomerModel customerModel) {
        return Customer.builder()
                .customerId(new CustomerId(UUID.fromString(customerModel.getId())))
                .typeIdentification(customerModel.getTypeIdentification())
                .documentNumber(customerModel.getDocumentNumber())
                .firstName (customerModel.getFirstName())
                .lastName(customerModel.getLastName())
                .email(customerModel.getEmail())
                .phone(customerModel.getPhone())
                .userRole(customerModel.getUserRole())
                .build();
    }

    private List<OrderItem> orderItemsToOrderItemEntities(
            List<com.fmattaperdomo.order.service.domain.dto.create.OrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItem ->
                        OrderItem.builder()
                                .productStore(new ProductStore(new ProductStoreId(orderItem.getProductStoreId())))
                                .price(new Money(orderItem.getPrice()))
                                .quantity(orderItem.getQuantity())
                                .subTotal(new Money(orderItem.getSubTotal()))
                                .build()).collect(Collectors.toList());
    }

    private Address orderAddressToAddress(OrderAddress orderAddress) {
        return new Address(
                UUID.randomUUID(),
                orderAddress.getStreet(),
                orderAddress.getCity(),
                orderAddress.getState(),
                orderAddress.getCountry(),
                orderAddress.getZipCode()
        );
    }
}
