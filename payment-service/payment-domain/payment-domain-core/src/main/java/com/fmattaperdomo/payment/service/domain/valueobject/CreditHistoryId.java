package com.fmattaperdomo.payment.service.domain.valueobject;

import com.fmattaperdomo.domain.valueobject.BaseId;

import java.util.UUID;

public class CreditHistoryId extends BaseId<UUID> {
    public CreditHistoryId(UUID value) {
        super(value);
    }
}

