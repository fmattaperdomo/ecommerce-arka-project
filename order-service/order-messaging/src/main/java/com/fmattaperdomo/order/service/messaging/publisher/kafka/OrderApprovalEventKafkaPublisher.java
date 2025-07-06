package com.fmattaperdomo.order.service.messaging.publisher.kafka;

import com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel;
import com.fmattaperdomo.kafka.producer.KafkaMessageHelper;
import com.fmattaperdomo.kafka.producer.service.KafkaProducer;
import com.fmattaperdomo.order.service.domain.config.OrderServiceConfigData;
import com.fmattaperdomo.order.service.domain.outbox.model.approval.OrderApprovalEventPayload;
import com.fmattaperdomo.order.service.domain.outbox.model.approval.OrderApprovalOutboxMessage;
import com.fmattaperdomo.order.service.domain.ports.output.message.publisher.storeapproval.StoreApprovalRequestMessagePublisher;
import com.fmattaperdomo.order.service.messaging.mapper.OrderMessagingDataMapper;
import com.fmattaperdomo.outbox.OutboxStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Slf4j
@Component
public class OrderApprovalEventKafkaPublisher implements StoreApprovalRequestMessagePublisher {

    private final OrderMessagingDataMapper orderMessagingDataMapper;
    private final KafkaProducer<String, StoreApprovalRequestAvroModel> kafkaProducer;
    private final OrderServiceConfigData orderServiceConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;

    public OrderApprovalEventKafkaPublisher(OrderMessagingDataMapper orderMessagingDataMapper,
                                            KafkaProducer<String, StoreApprovalRequestAvroModel> kafkaProducer,
                                            OrderServiceConfigData orderServiceConfigData,
                                            KafkaMessageHelper kafkaMessageHelper) {
        this.orderMessagingDataMapper = orderMessagingDataMapper;
        this.kafkaProducer = kafkaProducer;
        this.orderServiceConfigData = orderServiceConfigData;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }


    @Override
    public void publish(OrderApprovalOutboxMessage orderApprovalOutboxMessage,
                        BiConsumer<OrderApprovalOutboxMessage, OutboxStatus> outboxCallback) {
        OrderApprovalEventPayload orderApprovalEventPayload =
                kafkaMessageHelper.getOrderEventPayload(orderApprovalOutboxMessage.getPayload(),
                        OrderApprovalEventPayload.class);

        String sagaId = orderApprovalOutboxMessage.getSagaId().toString();

        log.info("Received OrderApprovalOutboxMessage for order id: {} and saga id: {}",
                orderApprovalEventPayload.getOrderId(),
                sagaId);

        try {
            StoreApprovalRequestAvroModel storeApprovalRequestAvroModel =
                    orderMessagingDataMapper
                            .orderApprovalEventToStoreApprovalRequestAvroModel(sagaId,
                                    orderApprovalEventPayload);

            kafkaProducer.send(orderServiceConfigData.getStoreApprovalRequestTopicName(),
                    sagaId,
                    storeApprovalRequestAvroModel,
                    kafkaMessageHelper.getKafkaCallback(orderServiceConfigData.getStoreApprovalRequestTopicName(),
                            storeApprovalRequestAvroModel,
                            orderApprovalOutboxMessage,
                            outboxCallback,
                            orderApprovalEventPayload.getOrderId(),
                            "StoreApprovalRequestAvroModel"));

            log.info("OrderApprovalEventPayload sent to kafka for order id: {} and saga id: {}",
                    storeApprovalRequestAvroModel.getOrderId(), sagaId);
        } catch (Exception e) {
            log.error("Error while sending OrderApprovalEventPayload to kafka for order id: {} and saga id: {}," +
                    " error: {}", orderApprovalEventPayload.getOrderId(), sagaId, e.getMessage());
        }


    }
}
