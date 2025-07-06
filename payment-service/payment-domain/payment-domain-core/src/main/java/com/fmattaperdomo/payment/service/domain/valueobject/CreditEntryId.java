package com.fmattaperdomo.payment.service.domain.valueobject;

import com.fmattaperdomo.domain.valueobject.BaseId;

import java.util.UUID;

public class CreditEntryId extends BaseId<UUID> {
    public CreditEntryId(UUID value) {
        super(value);
    }
}

