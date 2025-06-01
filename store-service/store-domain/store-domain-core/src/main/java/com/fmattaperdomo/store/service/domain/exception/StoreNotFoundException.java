package com.fmattaperdomo.store.service.domain.exception;

import com.fmattaperdomo.domain.exception.DomainException;

public class StoreNotFoundException extends DomainException {
    public StoreNotFoundException(String message) {
        super(message);
    }

    public StoreNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
