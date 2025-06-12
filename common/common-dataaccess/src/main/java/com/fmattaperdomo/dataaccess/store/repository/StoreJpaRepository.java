package com.fmattaperdomo.dataaccess.store.repository;

import com.fmattaperdomo.dataaccess.store.entity.StoreEntity;
import com.fmattaperdomo.dataaccess.store.entity.StoreEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StoreJpaRepository extends JpaRepository<StoreEntity, StoreEntityId> {
    Optional<List<StoreEntity>> findByStoreIdAndProductStoreIdIn(UUID storeId, List<UUID> productStoreIds);
}
