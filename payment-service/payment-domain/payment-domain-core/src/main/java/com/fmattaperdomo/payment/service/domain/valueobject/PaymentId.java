package com.fmattaperdomo.payment.service.domain.valueobject;

import com.fmattaperdomo.domain.valueobject.BaseId;

import java.util.UUID;

public class PaymentId extends BaseId<UUID> {
    public PaymentId(UUID value) {
        super(value);
    }
}

