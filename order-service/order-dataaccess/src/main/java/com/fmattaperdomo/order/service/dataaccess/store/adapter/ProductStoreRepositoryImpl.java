package com.fmattaperdomo.order.service.dataaccess.store.adapter;

import com.fmattaperdomo.dataaccess.store.entity.ProductStoreEntity;
import com.fmattaperdomo.dataaccess.store.repository.StoreJpaRepository;
import com.fmattaperdomo.order.service.dataaccess.store.mapper.ProductStoreDataAccessMapper;
import com.fmattaperdomo.order.service.domain.entity.Store;
import com.fmattaperdomo.order.service.domain.ports.output.repository.StoreRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ProductStoreRepositoryImpl implements StoreRepository {

    private final StoreJpaRepository storeJpaRepository;
    private final ProductStoreDataAccessMapper storeDataAccessMapper;

    public ProductStoreRepositoryImpl(StoreJpaRepository storeJpaRepository,
                                    ProductStoreDataAccessMapper storeDataAccessMapper) {
        this.storeJpaRepository = storeJpaRepository;
        this.storeDataAccessMapper = storeDataAccessMapper;
    }

    @Override
    public Optional<Store> findStoreInformation(Store store) {
        List<UUID> productsStore =
                storeDataAccessMapper.storeToProductsStore(store);
        Optional<List<ProductStoreEntity>> storeEntities = storeJpaRepository
                .findByStoreIdAndProductStoreIdIn(store.getId().getValue(),
                        productsStore);
        return storeEntities.map(storeDataAccessMapper::storeEntityToStore);
    }
}

