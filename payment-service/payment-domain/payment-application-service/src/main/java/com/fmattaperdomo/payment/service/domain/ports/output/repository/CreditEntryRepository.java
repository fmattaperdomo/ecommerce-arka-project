package com.fmattaperdomo.payment.service.domain.ports.output.repository;

import com.fmattaperdomo.domain.valueobject.CustomerId;
import com.fmattaperdomo.payment.service.domain.entity.CreditEntry;

import java.util.Optional;

public interface CreditEntryRepository {

    CreditEntry save(CreditEntry creditEntry);

    Optional<CreditEntry> findByCustomerId(CustomerId customerId);
}
