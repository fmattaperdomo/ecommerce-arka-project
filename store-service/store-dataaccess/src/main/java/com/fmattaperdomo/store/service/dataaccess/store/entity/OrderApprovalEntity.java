package com.fmattaperdomo.store.service.dataaccess.store.entity;

import com.fmattaperdomo.domain.valueobject.OrderApprovalStatus;
import lombok.*;

import jakarta.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_approval", schema = "store")
@Entity
public class OrderApprovalEntity {

    @Id
    private UUID id;
    private UUID storeId;
    private UUID orderId;
    @Enumerated(EnumType.STRING)
    private OrderApprovalStatus status;
}

