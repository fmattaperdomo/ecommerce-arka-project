package com.fmattaperdomo.dataaccess.store.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductStoreEntityId implements Serializable {

    private UUID storeId;
    private UUID productStoreId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductStoreEntityId that = (ProductStoreEntityId) o;
        return storeId.equals(that.storeId) && productStoreId.equals(that.productStoreId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, productStoreId);
    }
}
