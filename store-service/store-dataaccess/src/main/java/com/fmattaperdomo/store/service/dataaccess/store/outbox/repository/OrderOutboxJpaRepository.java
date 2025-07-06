package com.fmattaperdomo.store.service.dataaccess.store.outbox.repository;

import com.fmattaperdomo.outbox.OutboxStatus;
import com.fmattaperdomo.store.service.dataaccess.store.outbox.entity.OrderOutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderOutboxJpaRepository extends JpaRepository<OrderOutboxEntity, UUID> {

    Optional<List<OrderOutboxEntity>> findByTypeAndOutboxStatus(String type, OutboxStatus outboxStatus);

    Optional<OrderOutboxEntity> findByTypeAndSagaIdAndOutboxStatus(String type, UUID sagaId, OutboxStatus outboxStatus);

    void deleteByTypeAndOutboxStatus(String type, OutboxStatus outboxStatus);

}
