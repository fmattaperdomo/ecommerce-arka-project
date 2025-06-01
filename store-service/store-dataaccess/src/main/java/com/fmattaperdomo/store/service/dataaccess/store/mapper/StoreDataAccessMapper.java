package com.fmattaperdomo.store.service.dataaccess.store.mapper;

import com.fmattaperdomo.dataaccess.store.entity.StoreEntity;
import com.fmattaperdomo.dataaccess.store.exception.StoreDataAccessException;
import com.fmattaperdomo.domain.valueobject.Money;
import com.fmattaperdomo.domain.valueobject.OrderId;
import com.fmattaperdomo.domain.valueobject.ProductId;
import com.fmattaperdomo.domain.valueobject.StoreId;
import com.fmattaperdomo.store.service.dataaccess.store.entity.OrderApprovalEntity;
import com.fmattaperdomo.store.service.domain.entity.OrderApproval;
import com.fmattaperdomo.store.service.domain.entity.OrderDetail;
import com.fmattaperdomo.store.service.domain.entity.Product;
import com.fmattaperdomo.store.service.domain.entity.Store;
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

    public Store storeEntityToStore(List<StoreEntity> storeEntities) {
        StoreEntity storeEntity =
                storeEntities.stream().findFirst().orElseThrow(() ->
                        new StoreDataAccessException("No stores found!"));

        List<ProductStore> productsStore = storeEntities.stream().map(entity ->
                        ProductStore.builder()
                                .storeId(new StoreId(entity.getStoreId()))
                                .productId(new ProductId(entity.getProductId()))
                                .price(new Money(entity.getPrice()))
                                .stockQuantity(entity.getStockQuantity())
                                .barCode(entity.getBarCode())
                                .supplierId(new SupplierId(entity.getSupplierId()))
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
