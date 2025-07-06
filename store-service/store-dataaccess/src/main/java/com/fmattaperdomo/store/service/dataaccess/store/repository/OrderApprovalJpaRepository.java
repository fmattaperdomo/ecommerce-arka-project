package com.fmattaperdomo.store.service.dataaccess.store.repository;

import com.fmattaperdomo.store.service.dataaccess.store.entity.OrderApprovalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderApprovalJpaRepository extends JpaRepository<OrderApprovalEntity, UUID> {


}
