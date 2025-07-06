package com.fmattaperdomo.store.service.domain.entity;

import com.fmattaperdomo.domain.entity.BaseEntity;
import com.fmattaperdomo.domain.valueobject.ProductFeatureId;

import java.util.UUID;

public class ProductFeature extends BaseEntity<ProductFeatureId> {
    private UUID productId;
    private UUID featureId;
    private String value;

    private ProductFeature(Builder builder) {
        setId(builder.productFeatureId);
        productId = builder.productId;
        featureId = builder.featureId;
        value = builder.value;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getProductId() {
        return productId;
    }

    public UUID getFeatureId() {
        return featureId;
    }

    public String getValue() {
        return value;
    }

    public static final class Builder {
        private ProductFeatureId productFeatureId;
        private UUID productId;
        private UUID featureId;
        private String value;

        private Builder() {
        }

        public Builder productId(UUID val) {
            productId = val;
            return this;
        }

        public Builder featureId(UUID val) {
            featureId = val;
            return this;
        }

        public Builder value(String val) {
            value = val;
            return this;
        }

        public ProductFeature build() {
            return new ProductFeature(this);
        }
    }
}
