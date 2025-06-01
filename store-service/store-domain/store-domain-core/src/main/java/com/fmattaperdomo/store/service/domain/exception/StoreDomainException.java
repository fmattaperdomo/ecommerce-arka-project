package com.fmattaperdomo.store.service.domain.exception;

import com.fmattaperdomo.domain.exception.DomainException;

public class StoreDomainException extends DomainException{
    public StoreDomainException(String message) {
        super(message);
    }

    public StoreDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}

