package com.fmattaperdomo.payment.service.dataaccess.credithistory.mapper;

import com.fmattaperdomo.domain.valueobject.CustomerId;
import com.fmattaperdomo.domain.valueobject.Money;
import com.fmattaperdomo.payment.service.dataaccess.credithistory.entity.CreditHistoryEntity;
import com.fmattaperdomo.payment.service.domain.entity.CreditHistory;
import com.fmattaperdomo.payment.service.domain.valueobject.CreditHistoryId;
import org.springframework.stereotype.Component;

@Component
public class CreditHistoryDataAccessMapper {

    public CreditHistory creditHistoryEntityToCreditHistory(CreditHistoryEntity creditHistoryEntity) {
        return CreditHistory.builder()
                .creditHistoryId(new CreditHistoryId(creditHistoryEntity.getId()))
                .customerId(new CustomerId(creditHistoryEntity.getCustomerId()))
                .amount(new Money(creditHistoryEntity.getAmount()))
                .transactionType(creditHistoryEntity.getType())
                .build();
    }

    public CreditHistoryEntity creditHistoryToCreditHistoryEntity(CreditHistory creditHistory) {
        return CreditHistoryEntity.builder()
                .id(creditHistory.getId().getValue())
                .customerId(creditHistory.getCustomerId().getValue())
                .amount(creditHistory.getAmount().getAmount())
                .type(creditHistory.getTransactionType())
                .build();
    }

}
