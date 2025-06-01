package com.fmattaperdomo.store.service.domain.valueobject;

import com.fmattaperdomo.domain.valueobject.BaseId;

import java.util.UUID;

public class OrderApprovalId extends BaseId<UUID> {
    public OrderApprovalId(UUID value) {
        super(value);
    }
}
