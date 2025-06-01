package com.fmattaperdomo.order.service.domain.entity;

import com.fmattaperdomo.domain.entity.BaseEntity;
import com.fmattaperdomo.domain.valueobject.*;

import java.time.ZonedDateTime;

public class ProductStore extends BaseEntity<ProductStoreId> {
    private StoreId storeId;
    private Product product;
    private Money price;
    private Integer stockQuantity;
    private String barcode;
    private SupplierId supplierId;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    private ProductStore(Builder builder) {
        super.setId(builder.productStoreId);
        storeId = builder.storeId;
        product = builder.product;
        price = builder.price;
        stockQuantity = builder.stockQuantity;
        barcode = builder.barcode;
        supplierId = builder.supplierId;
        createdAt = ZonedDateTime.now();
        updatedAt = ZonedDateTime.now();
    }

    public static Builder builder() {
        return new Builder();
    }

    public ProductStore(ProductStoreId productStoreId) {
        super.setId(productStoreId);
    }

    public StoreId getStoreId() {
        return storeId;
    }

    public Product getProduct() {
        return product;
    }

    public Money getPrice() {
        return price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public String getBarcode() {
        return barcode;
    }

    public SupplierId getSupplierId() {
        return supplierId;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }


    public void updateWithConfirmedProductAndPrice(StoreId storeId, Product product, Money price, Integer stockQuantity, String barcode, SupplierId supplierId) {
        this.storeId = storeId;
        this.product = product;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.barcode = barcode;
        this.supplierId = supplierId;
        createdAt = ZonedDateTime.now();
        updatedAt = ZonedDateTime.now();
    }

    public static final class Builder {
        private ProductStoreId productStoreId;
        private StoreId storeId;
        private Product product;
        private Money price;
        private Integer stockQuantity;
        private String barcode;
        private SupplierId supplierId;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;

        private Builder() {
        }

        public Builder storeId(StoreId val) {
            storeId = val;
            return this;
        }

        public Builder productStoreId(ProductStoreId val) {
            productStoreId = val;
            return this;
        }

        public Builder product(Product val) {
            product = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder stockQuantity(Integer val) {
            stockQuantity = val;
            return this;
        }

        public Builder barcode(String val) {
            barcode = val;
            return this;
        }

        public Builder supplierId(SupplierId val) {
            supplierId = val;
            return this;
        }

        public ProductStore build() {
            return new ProductStore(this);
        }
    }
}
