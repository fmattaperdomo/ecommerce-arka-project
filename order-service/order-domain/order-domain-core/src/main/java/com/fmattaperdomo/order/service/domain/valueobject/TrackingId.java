package com.fmattaperdomo.order.service.domain.valueobject;

import com.fmattaperdomo.domain.valueobject.BaseId;

import java.util.UUID;

public class TrackingId extends BaseId<UUID> {
    public TrackingId(UUID value) {
        super(value);
    }
}
