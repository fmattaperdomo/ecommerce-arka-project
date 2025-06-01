package com.fmattaperdomo.supplier.service.domain.create;

import com.fmattaperdomo.domain.valueobject.Address;
import com.fmattaperdomo.domain.valueobject.Identification;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateSupplierCommand {
    @NotNull
    private final UUID supplierId;
    @NotNull
    private final Identification identification;
    @NotNull
    private final String firstName;
    @NotNull
    private final String lastName;
    @NotNull
    private final String email;
    @NotNull
    private final String phone;
    @NotNull
    private final SupplierRole role;
    @NotNull
    private final Address address;
}
