package com.fmattaperdomo.order.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class OrderAddress {
    @NotNull
    @Max(value = 100)
    private final String street;
    @NotNull
    @Max(value = 100)
    private final String city;
    @NotNull
    @Max(value = 100)
    private final String state;
    @NotNull
    @Max(value = 100)
    private final String country;
    @NotNull
    @Max(value = 10)
    private final String zipCode;
}
