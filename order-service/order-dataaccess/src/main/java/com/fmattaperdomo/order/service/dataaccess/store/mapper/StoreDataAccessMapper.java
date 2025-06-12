package com.fmattaperdomo.order.service.dataaccess.store.mapper;

import com.fmattaperdomo.dataaccess.store.entity.StoreEntity;
import com.fmattaperdomo.dataaccess.store.exception.StoreDataAccessException;
import com.fmattaperdomo.domain.valueobject.*;
import com.fmattaperdomo.order.service.domain.entity.Product;
import com.fmattaperdomo.order.service.domain.entity.ProductStore;
import com.fmattaperdomo.order.service.domain.entity.Store;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class StoreDataAccessMapper {

    public List<UUID> storeToProductsStore(Store store) {
        return store.getProductsStore().stream()
                .map(productStore -> productStore.getId().getValue())
                .collect(Collectors.toList());
    }

    public Store storeEntityToStore(List<StoreEntity> storeEntities) {
        StoreEntity storeEntity =
                storeEntities.stream().findFirst().orElseThrow(() ->
                        new StoreDataAccessException("Store could not be found!"));

        List<ProductStore> productsStore = storeEntities.stream().map(entity -> ProductStore.builder()
                        .productStoreId(new ProductStoreId(entity.getProductStoreId()))
                        .storeId(new StoreId(entity.getStoreId()))
                        .price(new Money(entity.getProductStorePrice()))
                        .barcode(entity.getProductStoreBarcode())
                        .stockQuantity(entity.getProductStoreStockQuantity())
                        .supplierId(new SupplierId(entity.getProductStoreSupplierId()))
                        .product(new Product(new ProductId(entity.getProductStoreProductId())))
                        .build())
                .collect(Collectors.toList());

        return Store.builder()
                .storeId(new StoreId(storeEntity.getStoreId()))
                .name(storeEntity.getStoreName())
                .description(storeEntity.getStoreDescription())
                .productsStore(productsStore)
                .active(storeEntity.getStoreActive())
                .build();
    }
}
