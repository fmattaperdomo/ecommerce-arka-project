package com.fmattaperdomo.order.service.dataaccess.outbox.storeapproval.repository;

import com.fmattaperdomo.order.service.dataaccess.outbox.storeapproval.entity.ApprovalOutboxEntity;
import com.fmattaperdomo.outbox.OutboxStatus;
import com.fmattaperdomo.saga.SagaStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ApprovalOutboxJpaRepository extends JpaRepository<ApprovalOutboxEntity, UUID> {

    Optional<List<ApprovalOutboxEntity>> findByTypeAndOutboxStatusAndSagaStatusIn(String type,
                                                                                  OutboxStatus outboxStatus,
                                                                                  List<SagaStatus> sagaStatus);

    Optional<ApprovalOutboxEntity> findByTypeAndSagaIdAndSagaStatusIn(String type,
                                                                      UUID sagaId,
                                                                      List<SagaStatus> sagaStatus);

    void deleteByTypeAndOutboxStatusAndSagaStatusIn(String type,
                                                    OutboxStatus outboxStatus,
                                                    List<SagaStatus> sagaStatus);

}

