package com.fmattaperdomo.store.service.dataaccess.store.adapter;

import com.fmattaperdomo.dataaccess.store.entity.StoreEntity;
import com.fmattaperdomo.dataaccess.store.repository.StoreJpaRepository;
import com.fmattaperdomo.store.service.dataaccess.store.mapper.StoreDataAccessMapper;
import com.fmattaperdomo.store.service.domain.entity.Store;
import com.fmattaperdomo.store.service.domain.ports.output.repository.StoreRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class StoreRepositoryImpl implements StoreRepository{

    private final StoreJpaRepository storeJpaRepository;
    private final StoreDataAccessMapper storeDataAccessMapper;

    public StoreRepositoryImpl(StoreJpaRepository storeJpaRepository,
                                    StoreDataAccessMapper storeDataAccessMapper) {
        this.storeJpaRepository = storeJpaRepository;
        this.storeDataAccessMapper = storeDataAccessMapper;
    }

    @Override
    public Optional<Store> findStoreInformation(Store store) {
        List<UUID> productsStore =
                storeDataAccessMapper.storeToProductsStore(store);
        Optional<List<StoreEntity>> storeEntities = storeJpaRepository
                .findByProductStoreIdAndStoreProductIdIn(store.getId().getValue(),
                        productsStore);
        return storeEntities.map(storeDataAccessMapper::storeEntityToStore);
    }
}
