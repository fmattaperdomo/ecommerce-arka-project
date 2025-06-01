package com.fmattaperdomo.order.service.dataaccess.outbox.storeapproval.exception;

public class ApprovalOutboxNotFoundException extends RuntimeException {

    public ApprovalOutboxNotFoundException(String message) {
        super(message);
    }
}
