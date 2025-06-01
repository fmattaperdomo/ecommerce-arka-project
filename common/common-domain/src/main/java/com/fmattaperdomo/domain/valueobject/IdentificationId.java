package com.fmattaperdomo.domain.valueobject;

import java.util.UUID;

public class IdentificationId extends BaseId<UUID>{
    public IdentificationId(UUID value) {
        super(value);
    }
}
