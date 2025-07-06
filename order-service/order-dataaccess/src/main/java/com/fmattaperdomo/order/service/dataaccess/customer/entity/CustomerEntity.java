package com.fmattaperdomo.order.service.dataaccess.customer.entity;

import com.fmattaperdomo.domain.valueobject.TypeIdentification;
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
    private String typeIdentification;
    private String documentNumber;    
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String userRole;
}

