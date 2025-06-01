package com.fmattaperdomo.store.service.domain.entity;

import com.fmattaperdomo.domain.entity.BaseEntity;
import com.fmattaperdomo.domain.valueobject.Brand;
import com.fmattaperdomo.domain.valueobject.Category;
import com.fmattaperdomo.domain.valueobject.ProductId;

import java.util.List;

public class Product extends BaseEntity<ProductId> {
    private String name;
    private String description;
    private Brand brand;
    private Category category;
    private String imagenUrl;
    List<ProductFeature> productFeatures;
    private boolean available;

    public void updateWithConfirmedNamePriceAndAvailability(String name,boolean available) {
        this.name = name;
        this.available = available;
    }
    private Product(Builder builder) {
        setId(builder.productId);
        name = builder.name;
        description = builder.description;
        brand = builder.brand;
        category = builder.category;
        imagenUrl = builder.imagenUrl;
        productFeatures = builder.productFeatures;
        available = builder.available;
    }
    public static Builder builder() {
        return new Builder();
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
    public String getImagenUrl() {
        return imagenUrl;
    }
    public List<ProductFeature> getProductFeatures() {
        return productFeatures;
    }

    public boolean isAvailable() {
        return available;
    }
    public static final class Builder {
        private ProductId productId;
        private String name;
        private String description;
        private Brand brand;
        private Category category;
        private String imagenUrl;
        private List<ProductFeature> productFeatures;
        private boolean available;

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

        public Builder imagenUrl(String val) {
            imagenUrl = val;
            return this;
        }

        public Builder productFeatures(List<ProductFeature> val) {
            productFeatures = val;
            return this;
        }

        public Builder available(boolean val) {
            available = val;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
