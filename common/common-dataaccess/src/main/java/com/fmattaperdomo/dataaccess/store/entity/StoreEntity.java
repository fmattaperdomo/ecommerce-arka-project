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
@IdClass(StoreEntityId.class)
@Table(name = "order_restaurant_m_view", schema = "restaurant")
@Entity
public class StoreEntity {

    @Id
    private UUID storeId;
    @Id
    private UUID storeProductId;
    private String storeName;
    private String storeDescription;
    private UUID storeAddressId;
    private UUID storeContactId;
    private Boolean storeActive;
    private UUID productStoreId;
    private UUID productId;
    private BigDecimal productStorePrice;
    private Integer productStoreStockQuantity;
    private String productStoreBarcode;
    private UUID productStoreSupplierId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreEntity that = (StoreEntity) o;
        return storeId.equals(that.storeId) && storeProductId.equals(that.storeProductId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, storeProductId);
    }
}

