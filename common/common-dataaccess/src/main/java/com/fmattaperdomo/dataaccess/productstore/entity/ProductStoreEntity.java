package com.fmattaperdomo.dataaccess.productstore.entity;

import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ProductStoreEntityId.class)
@Table(name = "order_product_store_m_view", schema = "store")
@Entity
public class ProductStoreEntity {
    @Id
    private UUID storeId;
    @Id
    private UUID productId;
    private BigDecimal price;
    private Integer stockQuantity;
    private String barCode;
    private UUID supplierId;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductStoreEntity that = (ProductStoreEntity) o;
        return storeId.equals(that.storeId) && productId.equals(that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, productId);
    }
}

