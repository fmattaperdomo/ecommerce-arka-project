package com.fmattaperdomo.dataaccess.productstore.repository;

import com.fmattaperdomo.dataaccess.productstore.entity.ProductStoreEntity;
import com.fmattaperdomo.dataaccess.productstore.entity.ProductStoreEntityId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductStoreJpaRepository extends JpaRepository<ProductStoreEntity, ProductStoreEntityId> {
    Optional<List<ProductStoreEntity>> findByProductStoreIdAndProductIdIn(UUID productStoreId, List<UUID> productIds);
}
