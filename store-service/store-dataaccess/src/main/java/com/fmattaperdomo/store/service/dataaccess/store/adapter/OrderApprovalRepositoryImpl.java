package com.fmattaperdomo.store.service.dataaccess.store.adapter;

import com.fmattaperdomo.store.service.dataaccess.store.repository.OrderApprovalJpaRepository;
import com.fmattaperdomo.store.service.dataaccess.store.mapper.StoreDataAccessMapper;
import com.fmattaperdomo.store.service.domain.entity.OrderApproval;
import com.fmattaperdomo.store.service.domain.ports.output.repository.OrderApprovalRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderApprovalRepositoryImpl implements OrderApprovalRepository {
    private final OrderApprovalJpaRepository orderApprovalJpaRepository;
    private final StoreDataAccessMapper storeDataAccessMapper;

    public OrderApprovalRepositoryImpl(OrderApprovalJpaRepository orderApprovalJpaRepository,
                                       StoreDataAccessMapper storeDataAccessMapper) {
        this.orderApprovalJpaRepository = orderApprovalJpaRepository;
        this.storeDataAccessMapper = storeDataAccessMapper;
    }

    @Override
    public OrderApproval save(OrderApproval orderApproval) {
        return storeDataAccessMapper
                .orderApprovalEntityToOrderApproval(orderApprovalJpaRepository
                        .save(storeDataAccessMapper.orderApprovalToOrderApprovalEntity(orderApproval)));
    }
}
