package com.fmattaperdomo.payment.service.domain.ports.output.repository;

import com.fmattaperdomo.domain.valueobject.CustomerId;
import com.fmattaperdomo.payment.service.domain.entity.CreditHistory;

import java.util.List;
import java.util.Optional;

public interface CreditHistoryRepository {

    CreditHistory save(CreditHistory creditHistory);

    Optional<List<CreditHistory>> findByCustomerId(CustomerId customerId);
}

