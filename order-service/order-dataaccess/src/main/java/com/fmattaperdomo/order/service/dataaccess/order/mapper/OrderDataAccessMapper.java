package com.fmattaperdomo.order.service.dataaccess.order.mapper;

import com.fmattaperdomo.domain.valueobject.*;
import com.fmattaperdomo.order.service.dataaccess.order.entity.OrderAddressEntity;
import com.fmattaperdomo.order.service.dataaccess.order.entity.OrderEntity;
import com.fmattaperdomo.order.service.dataaccess.order.entity.OrderItemEntity;
import com.fmattaperdomo.order.service.domain.entity.Order;
import com.fmattaperdomo.order.service.domain.entity.OrderItem;
import com.fmattaperdomo.order.service.domain.entity.ProductStore;
import com.fmattaperdomo.order.service.domain.valueobject.OrderItemId;
import com.fmattaperdomo.order.service.domain.valueobject.TrackingId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.fmattaperdomo.order.service.domain.entity.Order.FAILURE_MESSAGE_DELIMITER;

@Component
public class OrderDataAccessMapper {

    public OrderEntity orderToOrderEntity(Order order) {
        OrderEntity orderEntity = OrderEntity.builder()
                .id(order.getId().getValue())
                .customerId(order.getCustomerId().getValue())
                .storeId(order.getStoreId().getValue())
                .trackingId(order.getTrackingId().getValue())
                .deliveryAddress(deliveryAddressToAddressEntity(order.getDeliveryAddress()))
                .totalAmount(order.getTotalAmount().getAmount())
                .items(orderItemsToOrderItemEntities(order.getItems()))
                .orderStatus(order.getOrderStatus())
                .failureMessages(order.getFailureMessages() != null ?
                        String.join(FAILURE_MESSAGE_DELIMITER, order.getFailureMessages()) : "")
                .build();
        orderEntity.getDeliveryAddress().setOrder(orderEntity);
        orderEntity.getItems().forEach(orderItemEntity -> orderItemEntity.setOrder(orderEntity));

        return orderEntity;
    }

    public Order orderEntityToOrder(OrderEntity orderEntity) {
        return Order.builder()
                .orderId(new OrderId(orderEntity.getId()))
                .customerId(new CustomerId(orderEntity.getCustomerId()))
                .storeId(new StoreId(orderEntity.getStoreId()))
                .deliveryAddress(addressEntityToDeliveryAddress(orderEntity.getDeliveryAddress()))
                .totalAmount(new Money(orderEntity.getTotalAmount()))
                .items(orderItemEntitiesToOrderItems(orderEntity.getItems()))
                .trackingId(new TrackingId(orderEntity.getTrackingId()))
                .orderStatus(orderEntity.getOrderStatus())
                .failureMessages(orderEntity.getFailureMessages().isEmpty() ? new ArrayList<>() :
                        new ArrayList<>(Arrays.asList(orderEntity.getFailureMessages()
                                .split(FAILURE_MESSAGE_DELIMITER))))
                .build();
    }

    private List<OrderItem> orderItemEntitiesToOrderItems(List<OrderItemEntity> items) {
        return items.stream()
                .map(orderItemEntity -> OrderItem.builder()
                        .orderItemId(new OrderItemId(orderItemEntity.getId()))
                        .productStore(new ProductStore(new ProductStoreId(orderItemEntity.getProductStoreId())))
                        .price(new Money(orderItemEntity.getPrice()))
                        .quantity(orderItemEntity.getQuantity())
                        .subTotal(new Money(orderItemEntity.getSubTotal()))
                        .build())
                .collect(Collectors.toList());
    }

    private Address addressEntityToDeliveryAddress(OrderAddressEntity address) {
        return new Address(address.getId(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                address.getZipcode());
    }

    private List<OrderItemEntity> orderItemsToOrderItemEntities(List<OrderItem> items) {
        return items.stream()
                .map(orderItem -> OrderItemEntity.builder()
                        .id(orderItem.getId().getValue())
                        .productStoreId(orderItem.getProductStore().getId().getValue())
                        .price(orderItem.getPrice().getAmount())
                        .quantity(orderItem.getQuantity())
                        .subTotal(orderItem.getSubTotal().getAmount())
                        .build())
                .collect(Collectors.toList());
    }

    private OrderAddressEntity deliveryAddressToAddressEntity(Address deliveryAddress) {
        return OrderAddressEntity.builder()
                .id(deliveryAddress.getId())
                .street(deliveryAddress.getStreet())
                .city(deliveryAddress.getCity())
                .state(deliveryAddress.getState())
                .country(deliveryAddress.getCountry())
                .zipcode(deliveryAddress.getZipcode())
                .build();
    }
}
