package com.fmattaperdomo.store.service.domain.ports.output.repository;


import com.fmattaperdomo.store.service.domain.entity.OrderApproval;

public interface OrderApprovalRepository {
    OrderApproval save(OrderApproval orderApproval);
}
