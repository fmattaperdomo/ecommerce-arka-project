package com.fmattaperdomo.store.service.domain;

import com.fmattaperdomo.store.service.domain.dto.StoreApprovalRequest;
import com.fmattaperdomo.store.service.domain.ports.input.message.listener.StoreApprovalRequestMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StoreApprovalRequestMessageListenerImpl implements StoreApprovalRequestMessageListener{

    private final StoreApprovalRequestHelper storeApprovalRequestHelper;

    public StoreApprovalRequestMessageListenerImpl(StoreApprovalRequestHelper
                                                                storeApprovalRequestHelper) {
        this.storeApprovalRequestHelper = storeApprovalRequestHelper;
    }

    @Override
    public void approveOrder(StoreApprovalRequest storeApprovalRequest) {
        storeApprovalRequestHelper.persistOrderApproval(storeApprovalRequest);
    }
}
