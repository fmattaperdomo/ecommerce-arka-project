package com.fmattaperdomo.store.service.domain.entity;

import com.fmattaperdomo.domain.entity.AggregateRoot;
import com.fmattaperdomo.domain.valueobject.Money;
import com.fmattaperdomo.domain.valueobject.OrderApprovalStatus;
import com.fmattaperdomo.domain.valueobject.OrderStatus;
import com.fmattaperdomo.domain.valueobject.StoreId;
import com.fmattaperdomo.store.service.domain.valueobject.OrderApprovalId;

import java.util.List;
import java.util.UUID;

public class Store extends AggregateRoot<StoreId>{
    private OrderApproval orderApproval;
    private boolean active;
    private final OrderDetail orderDetail;

    public void validateOrder(List<String> failureMessages) {
        if (orderDetail.getOrderStatus() != OrderStatus.PAID) {
            failureMessages.add("Payment is not completed for order: " + orderDetail.getId());
        }
        Money totalAmount = orderDetail.getProductsStore().stream().map(productStore -> {
            if (!productStore.isAvailable()) {
                failureMessages.add("Product with id: " + productStore.getId().getValue()
                        + " is not available");
            }
            return productStore.getPrice().multiply(productStore.getQuantity());
        }).reduce(Money.ZERO, Money::add);

        if (!totalAmount.equals(orderDetail.getTotalAmount())) {
            failureMessages.add("Price total is not correct for order: " + orderDetail.getId());
        }
    }

    public void constructOrderApproval(OrderApprovalStatus orderApprovalStatus) {
        this.orderApproval = OrderApproval.builder()
                .orderApprovalId(new OrderApprovalId(UUID.randomUUID()))
                .storeId(this.getId())
                .orderId(this.getOrderDetail().getId())
                .approvalStatus(orderApprovalStatus)
                .build();
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private Store(Builder builder) {
        setId(builder.storeId);
        orderApproval = builder.orderApproval;
        active = builder.active;
        orderDetail = builder.orderDetail;
    }

    public static Builder builder() {
        return new Builder();
    }

    public OrderApproval getOrderApproval() {
        return orderApproval;
    }

    public boolean isActive() {
        return active;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public static final class Builder {
        private StoreId storeId;
        private OrderApproval orderApproval;
        private boolean active;
        private OrderDetail orderDetail;

        private Builder() {
        }

        public Builder storeId(StoreId val) {
            storeId = val;
            return this;
        }

        public Builder orderApproval(OrderApproval val) {
            orderApproval = val;
            return this;
        }

        public Builder active(boolean val) {
            active = val;
            return this;
        }

        public Builder orderDetail(OrderDetail val) {
            orderDetail = val;
            return this;
        }

        public Store build() {
            return new Store(this);
        }
    }
}
