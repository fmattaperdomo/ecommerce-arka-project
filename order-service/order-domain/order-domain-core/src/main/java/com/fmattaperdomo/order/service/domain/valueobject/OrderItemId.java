package com.fmattaperdomo.order.service.domain.valueobject;

import com.fmattaperdomo.domain.valueobject.BaseId;

public class OrderItemId extends BaseId<Long> {
    public OrderItemId(Long value) {
        super(value);
    }
}

