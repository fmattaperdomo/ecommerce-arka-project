package com.fmattaperdomo.order.service.domain.entity;

import com.fmattaperdomo.domain.entity.BaseEntity;
import com.fmattaperdomo.domain.valueobject.Feature;
import com.fmattaperdomo.domain.valueobject.ProductFeatureId;
import com.fmattaperdomo.domain.valueobject.ProductId;

import java.time.ZonedDateTime;

public class ProductFeature extends BaseEntity<ProductFeatureId> {
    private ProductId productId;
    private Feature feature;
    private String value;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    private ProductFeature(Builder builder) {
        super.setId(builder.productFeatureId);
        productId = builder.productId;
        feature = builder.feature;
        value = builder.value;
        createdAt = builder.createdAt;
        updatedAt = builder.updatedAt;
    }
    public static Builder builder() {
        return new Builder();
    }

    public ProductId getProductId() { return productId;}

    public Feature getFeature() { return feature; }

    public String getValue() { return value; }

    public ZonedDateTime getCreatedAt() { return createdAt; }

    public ZonedDateTime getUpdatedAt() { return updatedAt; }

    public static final class Builder {
        private ProductFeatureId productFeatureId;
        private ProductId productId;
        private Feature feature;
        private String value;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder productFeatureId(ProductFeatureId val) {
            productFeatureId = val;
            return this;
        }

        public Builder productId(ProductId val) {
            productId = val;
            return this;
        }

        public Builder feature(Feature val) {
            feature = val;
            return this;
        }

        public Builder value(String val) {
            value = val;
            return this;
        }

        public Builder createdAt(ZonedDateTime val) {
            createdAt = val;
            return this;
        }

        public Builder updatedAt(ZonedDateTime val) {
            updatedAt = val;
            return this;
        }

        public ProductFeature build() {
            return new ProductFeature(this);
        }
    }
}
