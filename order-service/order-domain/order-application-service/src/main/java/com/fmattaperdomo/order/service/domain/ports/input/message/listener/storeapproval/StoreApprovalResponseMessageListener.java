package com.fmattaperdomo.order.service.domain.ports.input.message.listener.storeapproval;

import com.fmattaperdomo.order.service.domain.dto.message.StoreApprovalResponse;

public interface StoreApprovalResponseMessageListener {

    void orderApproved(StoreApprovalResponse storeApprovalResponse);

    void orderRejected(StoreApprovalResponse storeApprovalResponse);
}

