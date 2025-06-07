package com.fmattaperdomo.order.service.domain;

import com.fmattaperdomo.order.service.domain.ports.output.message.publisher.payment.PaymentRequestMessagePublisher;
import com.fmattaperdomo.order.service.domain.ports.output.message.publisher.storeapproval.StoreApprovalRequestMessagePublisher;
import com.fmattaperdomo.order.service.domain.ports.output.repository.*;

import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.fmattaperdomo")
public class OrderTestConfiguration {

    @Bean
    public PaymentRequestMessagePublisher paymentRequestMessagePublisher() {
        return Mockito.mock(PaymentRequestMessagePublisher.class);
    }

    @Bean
    public StoreApprovalRequestMessagePublisher storeApprovalRequestMessagePublisher() {
        return Mockito.mock(StoreApprovalRequestMessagePublisher.class);
    }

    @Bean
    public OrderRepository orderRepository() {
        return Mockito.mock(OrderRepository.class);
    }

    @Bean
    public CustomerRepository customerRepository() {
        return Mockito.mock(CustomerRepository.class);
    }

    @Bean
    public StoreRepository storeRepository() {
        return Mockito.mock(StoreRepository.class);
    }

    @Bean
    public PaymentOutboxRepository paymentOutboxRepository() {
        return Mockito.mock(PaymentOutboxRepository.class);
    }

    @Bean
    public ApprovalOutboxRepository approvalOutboxRepository() {
        return Mockito.mock(ApprovalOutboxRepository.class);
    }

    @Bean
    public OrderDomainService orderDomainService() {
        return new OrderDomainServiceImpl();
    }

}
