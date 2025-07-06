package com.fmattaperdomo.store.service.domain.ports.output.repository;

import com.fmattaperdomo.store.service.domain.entity.Store;

import java.util.Optional;

public interface StoreRepository {
    Optional<Store> findStoreInformation(Store store);
}
