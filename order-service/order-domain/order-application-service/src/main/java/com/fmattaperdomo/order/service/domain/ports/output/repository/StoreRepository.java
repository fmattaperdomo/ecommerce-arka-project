package com.fmattaperdomo.order.service.domain.ports.output.repository;

import com.fmattaperdomo.order.service.domain.entity.Store;

import java.util.Optional;

public interface StoreRepository {

    Optional<Store> findStoreInformation(Store store);
}
