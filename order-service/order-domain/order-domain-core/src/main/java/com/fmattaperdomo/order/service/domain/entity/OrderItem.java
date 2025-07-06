package com.fmattaperdomo.order.service.domain.entity;

import com.fmattaperdomo.domain.entity.BaseEntity;
import com.fmattaperdomo.domain.valueobject.Money;
import com.fmattaperdomo.domain.valueobject.OrderId;
import com.fmattaperdomo.domain.valueobject.ProductId;
import com.fmattaperdomo.order.service.domain.valueobject.OrderItemId;

public class OrderItem extends BaseEntity<OrderItemId> {
    private OrderId orderId;
    private final ProductStore productStore;
    private final Integer quantity;
    private final Money price;
    private final Money subTotal;

    void initializeOrderItem(OrderId orderId, OrderItemId orderItemId) {
        this.orderId = orderId;
        super.setId(orderItemId);
    }

    boolean isPriceValid() {
        return price.isGreaterThanZero() &&
                price.equals(productStore.getPrice()) &&
                price.multiply(quantity).equals(subTotal);
    }

    private OrderItem(Builder builder) {
        super.setId(builder.orderItemId);
        productStore = builder.productStore;
        quantity = builder.quantity;
        price = builder.price;
        subTotal = builder.subTotal;
    }

    public static Builder builder() {
        return new Builder();
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public ProductStore getProductStore() {
        return productStore;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }

    public Money getSubTotal() {
        return subTotal;
    }

    public static final class Builder {
        private OrderItemId orderItemId;
        private ProductStore productStore;
        private Integer quantity;
        private Money price;
        private Money subTotal;

        private Builder() {
        }

        public Builder orderItemId(OrderItemId val) {
            orderItemId = val;
            return this;
        }

        public Builder productStore(ProductStore val) {
            productStore = val;
            return this;
        }

        public Builder quantity(Integer val) {
            quantity = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder subTotal(Money val) {
            subTotal = val;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
