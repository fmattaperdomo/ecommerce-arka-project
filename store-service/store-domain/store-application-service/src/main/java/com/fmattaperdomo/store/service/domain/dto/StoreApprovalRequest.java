package com.fmattaperdomo.store.service.domain.dto;

import com.fmattaperdomo.domain.valueobject.StoreOrderStatus;
import com.fmattaperdomo.store.service.domain.entity.ProductStore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class StoreApprovalRequest {
    private String id;
    private String sagaId;
    private String storeId;
    private String orderId;
    private StoreOrderStatus storeOrderStatus;
    private java.util.List<ProductStore> productsStore;
    private java.math.BigDecimal price;
    private java.time.Instant createdAt;
}
