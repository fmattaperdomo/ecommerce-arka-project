package com.fmattaperdomo.dataaccess.store.entity;
import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ProductStoreEntityId.class)
@Table(name = "order_productstore_m_view", schema = "store")
@Entity
public class ProductStoreEntity {

    @Id
    private UUID storeId;
    @Id
    private UUID productStoreId;
    private String storeName;
    private String storeDescription;
    private Boolean storeActive;
    private UUID productStoreProductId;
    private String productStoreProductName;
    private BigDecimal productStorePrice;
    private Integer productStoreStockQuantity;
    private String productStoreBarcode;
    private UUID productStoreSupplierId;
    private Boolean productStoreAvailable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductStoreEntity that = (ProductStoreEntity) o;
        return storeId.equals(that.storeId) && productStoreId.equals(that.productStoreId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, productStoreId);
    }
}

