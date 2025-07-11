package com.fmattaperdomo.payment.service.domain.ports.input.message.listener;

import com.fmattaperdomo.payment.service.domain.dto.PaymentRequest;

public interface PaymentRequestMessageListener {

    void completePayment(PaymentRequest paymentRequest);

    void cancelPayment(PaymentRequest paymentRequest);
}

