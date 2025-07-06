package com.fmattaperdomo.order.service.domain.ports.input.message.listener.payment;

import com.fmattaperdomo.order.service.domain.dto.message.PaymentResponse;

public interface PaymentResponseMessageListener {

    void paymentCompleted(PaymentResponse paymentResponse);

    void paymentCancelled(PaymentResponse paymentResponse);
}
