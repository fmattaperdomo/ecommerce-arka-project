package com.fmattaperdomo.store.service.domain.entity;

import com.fmattaperdomo.domain.entity.BaseEntity;
import com.fmattaperdomo.domain.valueobject.Money;
import com.fmattaperdomo.domain.valueobject.ProductStoreId;
import com.fmattaperdomo.domain.valueobject.SupplierId;

import java.util.UUID;

public class ProductStore extends BaseEntity<ProductStoreId> {
    private UUID storeId;
    private Product product;
    private Money price;
    private final int quantity;
    private final int stockQuantity;
    private String barCode;
    private SupplierId supplierId;
    private boolean available;

    public void updateWithConfirmedNamePriceAndAvailability(String barCode, Money price, boolean available) {
        this.barCode = barCode;
        this.price = price;
        this.available = available;
    }

    private ProductStore(Builder builder) {
        setId(builder.productStoreId);
        storeId = builder.storeId;
        product = builder.product;
        barCode = builder.barCode;
        quantity = builder.quantity;
        price = builder.price;
        stockQuantity = builder.stockQuantity;
        supplierId = builder.supplierId;
        available = builder.available;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Product getProduct() {
        return product;
    }

    public UUID getStoreId() {
        return storeId;
    }

    public String getBarCode() {
        return barCode;
    }

    public Money getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public SupplierId  getSupplierId() {
        return supplierId;
    }

    public boolean isAvailable() {
        return available;
    }


    public static final class Builder {
        private ProductStoreId productStoreId;
        private UUID storeId;
        private Product product;
        private String barCode;
        private Money price;
        private int quantity;
        private int stockQuantity;
        private SupplierId supplierId;
        private boolean available;

        private Builder() {
        }

        public Builder product(Product val) {
            product = val;
            return this;
        }

        public Builder storeId(UUID val) {
            storeId = val;
            return this;
        }

        public Builder productStoreId(ProductStoreId val) {
            productStoreId = val;
            return this;
        }

        public Builder barCode(String val) {
            barCode = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder quantity(int val) {
            quantity = val;
            return this;
        }

        public Builder stockQuantity(int val) {
            stockQuantity = val;
            return this;
        }

        public Builder supplierID (SupplierId val) {
            supplierId = val;
            return this;
        }

        public Builder available(boolean val) {
            available = val;
            return this;
        }

        public ProductStore build() {
            return new ProductStore(this);
        }
    }
}
