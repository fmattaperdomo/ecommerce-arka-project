package com.fmattaperdomo.customer.service.dataaccess.customer.entity;


import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

import com.fmattaperdomo.domain.valueobject.TypeIdentification;
import com.fmattaperdomo.domain.valueobject.UserRole;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
@Entity
public class CustomerEntity {
    @Id
    private UUID id;
    private TypeIdentification typeIdentification;
    private String documentNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole userRole;
}
