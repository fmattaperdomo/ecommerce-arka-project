package com.fmattaperdomo.order.service.dataaccess.customer.entity;

import com.fmattaperdomo.domain.valueobject.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

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
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private CustomerIdentificationEntity customerIdentification;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private CustomerAddressEntity customerAddress;

}

