package com.fmattaperdomo.store.service.domain.entity;

import com.fmattaperdomo.domain.entity.BaseEntity;
import com.fmattaperdomo.domain.valueobject.OrderApprovalStatus;
import com.fmattaperdomo.domain.valueobject.OrderId;
import com.fmattaperdomo.domain.valueobject.StoreId;
import com.fmattaperdomo.store.service.domain.valueobject.OrderApprovalId;

public class OrderApproval extends BaseEntity<OrderApprovalId> {
    private final StoreId storeId;
    private final OrderId orderId;
    private final OrderApprovalStatus approvalStatus;

    private OrderApproval(Builder builder) {
        setId(builder.orderApprovalId);
        storeId = builder.storeId;
        orderId = builder.orderId;
        approvalStatus = builder.approvalStatus;
    }

    public static Builder builder() {
        return new Builder();
    }


    public StoreId getStoreId() {
        return storeId;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public OrderApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public static final class Builder {
        private OrderApprovalId orderApprovalId;
        private StoreId storeId;
        private OrderId orderId;
        private OrderApprovalStatus approvalStatus;

        private Builder() {
        }

        public Builder orderApprovalId(OrderApprovalId val) {
            orderApprovalId = val;
            return this;
        }

        public Builder storeId(StoreId val) {
            storeId = val;
            return this;
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder approvalStatus(OrderApprovalStatus val) {
            approvalStatus = val;
            return this;
        }

        public OrderApproval build() {
            return new OrderApproval(this);
        }
    }
}
