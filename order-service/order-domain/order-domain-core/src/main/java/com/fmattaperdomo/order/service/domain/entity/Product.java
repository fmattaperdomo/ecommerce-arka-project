package com.fmattaperdomo.order.service.domain.entity;

import com.fmattaperdomo.domain.entity.BaseEntity;
import com.fmattaperdomo.domain.valueobject.Brand;
import com.fmattaperdomo.domain.valueobject.Category;
import com.fmattaperdomo.domain.valueobject.ProductId;

import java.time.ZonedDateTime;
import java.util.List;

public class Product extends BaseEntity<ProductId> {
    private String name;
    private String description;
    private Brand brand;
    private Category category;
    private String imageUrl;
    private List<ProductFeature> productFeatures;
    private Boolean active;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    private Product(Builder builder) {
        super.setId(builder.productId);
        name = builder.name;
        description = builder.description;
        brand = builder.brand;
        category = builder.category;
        imageUrl = builder.imageUrl;
        productFeatures = builder.productFeatures;
        active = builder.active;
        createdAt = ZonedDateTime.now();
        updatedAt = ZonedDateTime.now();
    }

    public static Builder builder() {
        return new Builder();
    }
    public Product(ProductId productId) {
        super.setId(productId);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Brand getBrand() {
        return brand;
    }

    public Category getCategory() {
        return category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<ProductFeature> getProductFeatures() {
        return productFeatures;
    }

    public Boolean getActive() {
        return active;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static final class Builder {
        private ProductId productId;
        private String name;
        private String description;
        private Brand brand;
        private Category category;
        private String imageUrl;
        private List<ProductFeature> productFeatures;
        private Boolean active;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;

        private Builder() {
        }

        public Builder productId(ProductId val) {
            productId = val;
            return this;
        }
        public Builder name(String val) {
            name = val;
            return this;
        }
        public Builder description(String val) {
            description = val;
            return this;
        }
        public Builder brand(Brand val) {
            brand = val;
            return this;
        }
        public Builder category(Category val) {
            category = val;
            return this;
        }
        public Builder imageUrl(String val) {
            imageUrl = val;
            return this;
        }
        public Builder productFeatures(List<ProductFeature> val) {
            productFeatures = val;
            return this;
        }
        public Builder active(Boolean val) {
            active = val;
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
        public Product build() {
            return new Product(this);
        }
    }


}
