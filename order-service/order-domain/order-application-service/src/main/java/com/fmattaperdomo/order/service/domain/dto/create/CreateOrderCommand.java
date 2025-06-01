package com.fmattaperdomo.order.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateOrderCommand {
    @NotNull
    private final UUID customerId;
    @NotNull
    private final UUID storeId;
    @NotNull
    private final BigDecimal totalAmount;
    @NotNull
    private final List<OrderItem> items;
    @NotNull
    private final OrderAddress address;
}
