package com.fmattaperdomo.outbox;

public interface OutboxScheduler {
    void processOutboxMessage();
}
