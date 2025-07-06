package com.fmattaperdomo.order.service.domain.dto.message;

import java.util.UUID;

import com.fmattaperdomo.domain.valueobject.Address;
import com.fmattaperdomo.domain.valueobject.TypeIdentification;
import com.fmattaperdomo.domain.valueobject.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CustomerModel {
    private String id;
    private String typeIdentification;
    private String documentNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String userRole;
}

