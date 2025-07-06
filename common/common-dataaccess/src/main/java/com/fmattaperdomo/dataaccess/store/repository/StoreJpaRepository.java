package com.fmattaperdomo.dataaccess.store.repository;

import com.fmattaperdomo.dataaccess.store.entity.ProductStoreEntity;
import com.fmattaperdomo.dataaccess.store.entity.ProductStoreEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StoreJpaRepository extends JpaRepository<ProductStoreEntity, ProductStoreEntityId> {
    Optional<List<ProductStoreEntity>> findByStoreIdAndProductStoreIdIn(UUID storeId, List<UUID> productStoreIds);
}
