package com.fmattaperdomo.order.service.domain.entity;

import com.fmattaperdomo.domain.entity.AggregateRoot;
import com.fmattaperdomo.domain.valueobject.*;
import com.fmattaperdomo.order.service.domain.exception.OrderDomainException;
import com.fmattaperdomo.order.service.domain.valueobject.OrderItemId;
import com.fmattaperdomo.order.service.domain.valueobject.TrackingId;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class Order extends AggregateRoot<OrderId> {
    private final CustomerId customerId;
    private final StoreId storeId;
    private final Address deliveryAddress;
    private final Money totalAmount;
    private final List<OrderItem> items;

    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;
    private final ZonedDateTime createdAt;
    private final ZonedDateTime updatedAt;

    public static final String FAILURE_MESSAGE_DELIMITER = ",";

    public void initializeOrder() {
        setId(new OrderId(UUID.randomUUID()));
        trackingId = new TrackingId(UUID.randomUUID());
        orderStatus = OrderStatus.PENDING;
        initializeOrderItems();
    }

    public void validateOrder() {
        validateInitialOrder();
        validateTotalPrice();
        validateItemsPrice();
    }

    public void pay() {
        if (orderStatus != OrderStatus.PENDING) {
            throw new OrderDomainException("Order is not in correct state for pay operation!");
        }
        orderStatus = OrderStatus.PAID;
    }

    public void approve() {
        if(orderStatus != OrderStatus.PAID) {
            throw new OrderDomainException("Order is not in correct state for approve operation!");
        }
        orderStatus = OrderStatus.APPROVED;
    }

    public void initCancel(List<String> failureMessages) {
        if (orderStatus != OrderStatus.PAID) {
            throw new OrderDomainException("Order is not in correct state for initCancel operation!");
        }
        orderStatus = OrderStatus.CANCELLING;
        updateFailureMessages(failureMessages);
    }

    public void cancel(List<String> failureMessages) {
        if (!(orderStatus == OrderStatus.CANCELLING || orderStatus == OrderStatus.PENDING)) {
            throw new OrderDomainException("Order is not in correct state for cancel operation!");
        }
        orderStatus = OrderStatus.CANCELLED;
        updateFailureMessages(failureMessages);
    }

    private void updateFailureMessages(List<String> failureMessages) {
        if (this.failureMessages != null && failureMessages != null) {
            this.failureMessages.addAll(failureMessages.stream().filter(message -> !message.isEmpty()).toList());
        }
        if (this.failureMessages == null) {
            this.failureMessages = failureMessages;
        }
    }

    private void validateInitialOrder() {
        if (orderStatus != null || getId() != null) {
            throw new OrderDomainException("Order is not in correct state for initialization!");
        }
    }

    private void validateTotalPrice() {
        if (totalAmount == null || !totalAmount.isGreaterThanZero()) {
            throw new OrderDomainException("Total price must be greater than zero!");
        }
    }

    private void validateItemsPrice() {
        Money orderItemsTotal = items.stream().map(orderItem -> {
            validateItemPrice(orderItem);
            return orderItem.getSubTotal();
        }).reduce(Money.ZERO, Money::add);

        if (!totalAmount.equals(orderItemsTotal)) {
            throw new OrderDomainException("Total price: " + totalAmount.getAmount()
                    + " is not equal to Order items total: " + orderItemsTotal.getAmount() + "!");
        }
    }

    private void validateItemPrice(OrderItem orderItem) {
        if (!orderItem.isPriceValid()) {
            throw new OrderDomainException("Order item price: " + orderItem.getPrice().getAmount() +
                    " is not valid for product store" + orderItem.getProductStore().getId().getValue());
        }
    }

    private void initializeOrderItems() {
        long itemId = 1;
        for (OrderItem orderItem: items) {
            orderItem.initializeOrderItem(super.getId(), new OrderItemId(itemId++));
        }
    }

    private Order(Builder builder) {
        super.setId(builder.orderId);
        customerId = builder.customerId;
        storeId = builder.storeId;
        deliveryAddress = builder.deliveryAddress;
        totalAmount = builder.totalAmount;
        items = builder.items;
        trackingId = builder.trackingId;
        orderStatus = builder.orderStatus;
        failureMessages = builder.failureMessages;

        createdAt = ZonedDateTime.now();
        updatedAt = ZonedDateTime.now();
    }

    public static Builder builder() {
        return new Builder();
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public StoreId getStoreId() {
        return storeId;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public Money getTotalAmount() { return totalAmount; }

    public List<OrderItem> getItems() {
        return items;
    }

    public TrackingId getTrackingId() {
        return trackingId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static final class Builder {
        private OrderId orderId;
        private CustomerId customerId;
        private StoreId storeId;
        private Address deliveryAddress;
        private Money totalAmount;
        private List<OrderItem> items;
        private TrackingId trackingId;
        private OrderStatus orderStatus;
        private List<String> failureMessages;

        private Builder() {
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder storeId(StoreId val) {
            storeId = val;
            return this;
        }

        public Builder deliveryAddress(Address val) {
            deliveryAddress = val;
            return this;
        }

        public Builder totalAmount(Money val) {
            totalAmount = val;
            return this;
        }

        public Builder items(List<OrderItem> val) {
            items = val;
            return this;
        }

        public Builder trackingId(TrackingId val) {
            trackingId = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder failureMessages(List<String> val) {
            failureMessages = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
