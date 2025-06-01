package com.fmattaperdomo.supplier.service.dataaccess.supplier.entity;

import com.fmattaperdomo.domain.valueobject.Address;
import com.fmattaperdomo.domain.valueobject.Identification;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "suppliers")
@Entity
public class SupplierEntity {
    @Id
    private UUID id;
    private Identification identification;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private SupplierRole role;
    private Address address;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
