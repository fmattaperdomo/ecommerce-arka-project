package com.fmattaperdomo.customer.service.domain.create;

import com.fmattaperdomo.domain.valueobject.Address;
import com.fmattaperdomo.domain.valueobject.Identification;
import com.fmattaperdomo.domain.valueobject.TypeIdentification;
import com.fmattaperdomo.domain.valueobject.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateCustomerCommand {
    @NotNull
    private final UUID customerId;
    @NotNull
    private final TypeIdentification typeIdentification;
    @NotNull
    private final String documentNumber;
    @NotNull
    private final String firstName;
    @NotNull
    private final String lastName;
    @NotNull
    private final String email;
    @NotNull
    private final String phone;
    @NotNull
    private final UserRole userRole;
}
