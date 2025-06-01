package com.fmattaperdomo.order.service.domain.outbox.model.approval;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class OrderApprovalEventPayload {
    @JsonProperty
    private String orderId;
    @JsonProperty
    private String storeId;
    @JsonProperty
    private BigDecimal price;
    @JsonProperty
    private ZonedDateTime createdAt;
    @JsonProperty
    private String storeOrderStatus;
    @JsonProperty
    private List<OrderApprovalEventProduct> productsStore;
}
