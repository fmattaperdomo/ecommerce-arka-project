package com.fmattaperdomo.store.service.dataaccess.store.mapper;

import com.fmattaperdomo.dataaccess.store.entity.ProductStoreEntity;
import com.fmattaperdomo.dataaccess.store.exception.StoreDataAccessException;
import com.fmattaperdomo.domain.valueobject.*;
import com.fmattaperdomo.store.service.dataaccess.store.entity.OrderApprovalEntity;
import com.fmattaperdomo.store.service.domain.entity.*;
import com.fmattaperdomo.store.service.domain.valueobject.OrderApprovalId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class StoreDataAccessMapper {
    public List<UUID> storeToProductsStore(Store store) {
        return store.getOrderDetail().getProductsStore().stream()
                .map(productStore -> productStore.getId().getValue())
                .collect(Collectors.toList());
    }

    public Store storeEntityToStore(List<ProductStoreEntity> storeEntities) {
        ProductStoreEntity storeEntity =
                storeEntities.stream().findFirst().orElseThrow(() ->
                        new StoreDataAccessException("No stores found!"));

        List<ProductStore> productsStore = storeEntities.stream().map(entity ->
                        ProductStore.builder()
                                .storeId(entity.getStoreId())
                                .productStoreId(new ProductStoreId(entity.getProductStoreId()))
                                .price(new Money(entity.getProductStorePrice()))
                                .stockQuantity(entity.getProductStoreStockQuantity())
                                .barCode(entity.getProductStoreBarcode())
                                .supplierID(new SupplierId(entity.getProductStoreSupplierId()))
                                .build())
                .collect(Collectors.toList());

        return Store.builder()
                .storeId(new StoreId(storeEntity.getStoreId()))
                .orderDetail(OrderDetail.builder()
                        .productsStore(productsStore)
                        .build())
                .active(storeEntity.getStoreActive())
                .build();
    }

    public OrderApprovalEntity orderApprovalToOrderApprovalEntity(OrderApproval orderApproval) {
        return OrderApprovalEntity.builder()
                .id(orderApproval.getId().getValue())
                .storeId(orderApproval.getStoreId().getValue())
                .orderId(orderApproval.getOrderId().getValue())
                .status(orderApproval.getApprovalStatus())
                .build();
    }

    public OrderApproval orderApprovalEntityToOrderApproval(OrderApprovalEntity orderApprovalEntity) {
        return OrderApproval.builder()
                .orderApprovalId(new OrderApprovalId(orderApprovalEntity.getId()))
                .storeId(new StoreId(orderApprovalEntity.getStoreId()))
                .orderId(new OrderId(orderApprovalEntity.getOrderId()))
                .approvalStatus(orderApprovalEntity.getStatus())
                .build();
    }

}
