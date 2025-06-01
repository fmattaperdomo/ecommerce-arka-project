package com.fmattaperdomo.store.service.domain.exception;

import com.fmattaperdomo.domain.exception.DomainException;

public class StoreApplicationServiceException  extends DomainException  {
    public StoreApplicationServiceException(String message) {
        super(message);
    }

    public StoreApplicationServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
