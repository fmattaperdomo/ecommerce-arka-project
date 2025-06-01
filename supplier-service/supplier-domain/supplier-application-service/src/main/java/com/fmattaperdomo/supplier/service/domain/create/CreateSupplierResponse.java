package com.fmattaperdomo.supplier.service.domain.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateSupplierResponse {
    @NotNull
    private final UUID supplierId;
    @NotNull
    private final String message;
}

