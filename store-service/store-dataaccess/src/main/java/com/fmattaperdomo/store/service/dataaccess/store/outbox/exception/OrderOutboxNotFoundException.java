package com.fmattaperdomo.store.service.dataaccess.store.outbox.exception;

public class OrderOutboxNotFoundException extends RuntimeException {

    public OrderOutboxNotFoundException(String message) {
        super(message);
    }
}
