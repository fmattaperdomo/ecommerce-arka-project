package com.fmattaperdomo.domain.valueobject;

import java.util.UUID;

public class SupplierId extends BaseId<UUID> {
    public SupplierId(UUID value) {
        super(value);
    }
}
