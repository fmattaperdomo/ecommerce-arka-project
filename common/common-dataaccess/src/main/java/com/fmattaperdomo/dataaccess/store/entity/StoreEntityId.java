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
public class StoreEntityId implements Serializable {

    private UUID storeId;
    private UUID storeProductId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreEntityId that = (StoreEntityId) o;
        return storeId.equals(that.storeId) && storeProductId.equals(that.storeProductId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, storeProductId);
    }
}
