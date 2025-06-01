package com.fmattaperdomo.store.service.domain.entity;

import com.fmattaperdomo.domain.entity.BaseEntity;
import com.fmattaperdomo.domain.valueobject.Money;
import com.fmattaperdomo.domain.valueobject.OrderId;
import com.fmattaperdomo.domain.valueobject.OrderStatus;

import java.util.List;

public class OrderDetail extends BaseEntity<OrderId> {
    private OrderStatus orderStatus;
    private Money totalAmount;
    private final List<ProductStore> productsStore;

    private OrderDetail(Builder builder) {
        setId(builder.orderId);
        orderStatus = builder.orderStatus;
        totalAmount = builder.totalAmount;
        productsStore = builder.productsStore;
    }

    public static Builder builder() {
        return new Builder();
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Money getTotalAmount() {
        return totalAmount;
    }

    public List<ProductStore> getProductsStore() {
        return productsStore;
    }

    public static final class Builder {
        private OrderId orderId;
        private OrderStatus orderStatus;
        private Money totalAmount;
        private List<ProductStore> productsStore;

        private Builder() {
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder totalAmount(Money val) {
            totalAmount = val;
            return this;
        }

        public Builder productsStore(List<ProductStore> val) {
            productsStore = val;
            return this;
        }

        public OrderDetail build() {
            return new OrderDetail(this);
        }
    }
}

