package com.fmattaperdomo.store.service.domain.ports.input.message.listener;

import com.fmattaperdomo.store.service.domain.dto.StoreApprovalRequest;

public interface StoreApprovalRequestMessageListener {
    void approveOrder(StoreApprovalRequest storeApprovalRequest);
}
