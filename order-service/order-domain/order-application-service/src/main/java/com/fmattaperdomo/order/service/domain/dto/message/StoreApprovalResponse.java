package com.fmattaperdomo.order.service.domain.dto.message;

import com.fmattaperdomo.domain.valueobject.OrderApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class StoreApprovalResponse {
    private String id;
    private String sagaId;
    private String orderId;
    private String storeId;
    private Instant createdAt;
    private OrderApprovalStatus orderApprovalStatus;
    private List<String> failureMessages;
}

