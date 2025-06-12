package com.fmattaperdomo.order.service.dataaccess.customer.entity;


import com.fmattaperdomo.domain.valueobject.TypeIdentification;
import com.fmattaperdomo.domain.valueobject.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_identification")
@Entity

public class CustomerIdentificationEntity {
    @Id
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_ID")
    private CustomerEntity customer;

    @Enumerated(EnumType.STRING)
    private TypeIdentification typeIdentification;

    private String documentNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerIdentificationEntity that = (CustomerIdentificationEntity) o;
        return typeIdentification.equals(that.typeIdentification) && documentNumber.equals(that.documentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeIdentification, documentNumber);
    }

}
