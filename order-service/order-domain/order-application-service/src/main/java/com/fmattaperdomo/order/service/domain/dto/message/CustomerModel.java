package com.fmattaperdomo.order.service.domain.dto.message;

import com.fmattaperdomo.domain.valueobject.Address;
import com.fmattaperdomo.domain.valueobject.Identification;
import com.fmattaperdomo.domain.valueobject.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CustomerModel {
    private String id;
    private Identification customerDocument;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Address customerAddress;
    private UserRole userRole;
}

