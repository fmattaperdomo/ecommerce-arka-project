package com.fmattaperdomo.store.service.domain;

import com.fmattaperdomo.store.service.domain.entity.Store;
import com.fmattaperdomo.store.service.domain.event.OrderApprovalEvent;

import java.util.List;

public interface StoreDomainService {

    OrderApprovalEvent validateOrder(Store store, List<String> failureMessages);
}
