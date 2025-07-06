package com.fmattaperdomo.store.service.messaging.publisher.kafka;

import com.fmattaperdomo.kafka.order.avro.model.StoreApprovalResponseAvroModel;
import com.fmattaperdomo.kafka.producer.KafkaMessageHelper;
import com.fmattaperdomo.kafka.producer.service.KafkaProducer;
import com.fmattaperdomo.outbox.OutboxStatus;
import com.fmattaperdomo.store.service.domain.config.StoreServiceConfigData;
import com.fmattaperdomo.store.service.domain.outbox.model.OrderEventPayload;
import com.fmattaperdomo.store.service.domain.outbox.model.OrderOutboxMessage;
import com.fmattaperdomo.store.service.domain.ports.output.message.publisher.StoreApprovalResponseMessagePublisher;
import com.fmattaperdomo.store.service.messaging.mapper.StoreMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Slf4j
@Component
public class StoreApprovalEventKafkaPublisher implements StoreApprovalResponseMessagePublisher {

    private final StoreMessagingDataMapper storeMessagingDataMapper;
    private final KafkaProducer<String, StoreApprovalResponseAvroModel> kafkaProducer;
    private final StoreServiceConfigData storeServiceConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;

    public StoreApprovalEventKafkaPublisher(StoreMessagingDataMapper dataMapper,
                                                 KafkaProducer<String, StoreApprovalResponseAvroModel>
                                                         kafkaProducer,
                                                 StoreServiceConfigData storeServiceConfigData,
                                                 KafkaMessageHelper kafkaMessageHelper) {
        this.storeMessagingDataMapper = dataMapper;
        this.kafkaProducer = kafkaProducer;
        this.storeServiceConfigData = storeServiceConfigData;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }


    @Override
    public void publish(OrderOutboxMessage orderOutboxMessage,
                        BiConsumer<OrderOutboxMessage, OutboxStatus> outboxCallback) {
        OrderEventPayload orderEventPayload =
                kafkaMessageHelper.getOrderEventPayload(orderOutboxMessage.getPayload(),
                        OrderEventPayload.class);

        String sagaId = orderOutboxMessage.getSagaId().toString();

        log.info("Received OrderOutboxMessage for order id: {} and saga id: {}",
                orderEventPayload.getOrderId(),
                sagaId);
        try {
            StoreApprovalResponseAvroModel storeApprovalResponseAvroModel =
                    storeMessagingDataMapper
                            .orderEventPayloadToStoreApprovalResponseAvroModel(sagaId, orderEventPayload);

            kafkaProducer.send(storeServiceConfigData.getStoreApprovalResponseTopicName(),
                    sagaId,
                    storeApprovalResponseAvroModel,
                    kafkaMessageHelper.getKafkaCallback(storeServiceConfigData
                                    .getStoreApprovalResponseTopicName(),
                            storeApprovalResponseAvroModel,
                            orderOutboxMessage,
                            outboxCallback,
                            orderEventPayload.getOrderId(),
                            "StoreApprovalResponseAvroModel"));

            log.info("StoreApprovalResponseAvroModel sent to kafka for order id: {} and saga id: {}",
                    storeApprovalResponseAvroModel.getOrderId(), sagaId);
        } catch (Exception e) {
            log.error("Error while sending StoreApprovalResponseAvroModel message" +
                            " to kafka with order id: {} and saga id: {}, error: {}",
                    orderEventPayload.getOrderId(), sagaId, e.getMessage());
        }
    }

}

