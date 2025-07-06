package com.fmattaperdomo.payment.service.domain.mapper;

import com.fmattaperdomo.domain.valueobject.CustomerId;
import com.fmattaperdomo.domain.valueobject.Money;
import com.fmattaperdomo.domain.valueobject.OrderId;
import com.fmattaperdomo.payment.service.domain.dto.PaymentRequest;
import com.fmattaperdomo.payment.service.domain.entity.Payment;
import com.fmattaperdomo.payment.service.domain.event.PaymentEvent;
import com.fmattaperdomo.payment.service.domain.outbox.model.OrderEventPayload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentDataMapper {

    public Payment paymentRequestModelToPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .orderId(new OrderId(UUID.fromString(paymentRequest.getOrderId())))
                .customerId(new CustomerId(UUID.fromString(paymentRequest.getCustomerId())))
                .price(new Money(paymentRequest.getPrice()))
                .build();
    }

    public OrderEventPayload paymentEventToOrderEventPayload(PaymentEvent paymentEvent) {
        return OrderEventPayload.builder()
                .paymentId(paymentEvent.getPayment().getId().getValue().toString())
                .customerId(paymentEvent.getPayment().getCustomerId().getValue().toString())
                .orderId(paymentEvent.getPayment().getOrderId().getValue().toString())
                .price(paymentEvent.getPayment().getPrice().getAmount())
                .createdAt(paymentEvent.getCreatedAt())
                .paymentStatus(paymentEvent.getPayment().getPaymentStatus().name())
                .failureMessages(paymentEvent.getFailureMessages())
                .build();
    }
}

