package com.fmattaperdomo.order.service.dataaccess.store.mapper;

import com.fmattaperdomo.dataaccess.store.entity.StoreEntity;
import com.fmattaperdomo.dataaccess.store.exception.StoreDataAccessException;
import com.fmattaperdomo.domain.valueobject.Money;
import com.fmattaperdomo.domain.valueobject.ProductId;
import com.fmattaperdomo.domain.valueobject.StoreId;
import com.fmattaperdomo.order.service.domain.entity.Product;
import com.fmattaperdomo.order.service.domain.entity.ProductStore;
import com.fmattaperdomo.order.service.domain.entity.Store;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class StoreDataAccessMapper {

    public List<UUID> storeToStoreProducts(Store store) {
        return store.getProductsStore().stream()
                .map(product -> product.getId().getValue())
                .collect(Collectors.toList());
    }

    public Store storeEntityToStore(List<StoreEntity> storeEntities) {
        StoreEntity storeEntity =
                storeEntities.stream().findFirst().orElseThrow(() ->
                        new StoreDataAccessException("Store could not be found!"));
/*** REVISAR
        List<ProductStore> storeProducts = storeEntities.stream().map(entity ->
                new ProductStore(new ProductId(entity.getProductId()), entity.getStoreName(),
                        new Money(entity.getProductStorePrice()))).toList();

        return Store.builder()
                .storeId(new StoreId(storeEntity.getStoreId()))
                ..productStore(storeEntity.ProductsStore)
                 .active(storeEntity.getStoreActive())
                .build();
 }
 ***/
        return null;
    }
}
