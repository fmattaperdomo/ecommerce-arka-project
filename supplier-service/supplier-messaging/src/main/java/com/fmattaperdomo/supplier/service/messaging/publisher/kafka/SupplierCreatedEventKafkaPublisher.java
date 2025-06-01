package com.fmattaperdomo.supplier.service.messaging.publisher.kafka;

import com.fmattaperdomo.supplier.service.domain.config.SupplierServiceConfigData;
import com.fmattaperdomo.supplier.service.domain.event.SupplierCreatedEvent;
import com.fmattaperdomo.supplier.service.domain.ports.output.message.publisher.SupplierMessagePublisher;
import com.fmattaperdomo.supplier.service.messaging.mapper.SupplierMessagingDataMapper;
import com.fmattaperdomo.kafka.order.avro.model.SupplierAvroModel;
import com.fmattaperdomo.kafka.producer.service.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Slf4j
@Component
public class SupplierCreatedEventKafkaPublisher implements SupplierMessagePublisher {

    private final SupplierMessagingDataMapper supplierMessagingDataMapper;

    private final KafkaProducer<String, SupplierAvroModel> kafkaProducer;

    private final SupplierServiceConfigData supplierServiceConfigData;

    public SupplierCreatedEventKafkaPublisher(SupplierMessagingDataMapper supplierMessagingDataMapper,
                                              KafkaProducer<String, SupplierAvroModel> kafkaProducer,
                                              SupplierServiceConfigData supplierServiceConfigData) {
        this.supplierMessagingDataMapper = supplierMessagingDataMapper;
        this.kafkaProducer = kafkaProducer;
        this.supplierServiceConfigData = supplierServiceConfigData;
    }

    @Override
    public void publish(SupplierCreatedEvent supplierCreatedEvent) {
        log.info("Received SupplierCreatedEvent for supplier id: {}",
                supplierCreatedEvent.getSupplier().getId().getValue());
        try {
            SupplierAvroModel supplierAvroModel = supplierMessagingDataMapper
                    .paymentResponseAvroModelToPaymentResponse(supplierCreatedEvent);

            kafkaProducer.send(supplierServiceConfigData.getSupplierTopicName(), supplierAvroModel.getId(),
                    supplierAvroModel,
                    getCallback(supplierServiceConfigData.getSupplierTopicName(), supplierAvroModel));

            log.info("SupplierCreatedEvent sent to kafka for supplier id: {}",
                    supplierAvroModel.getId());
        } catch (Exception e) {
            log.error("Error while sending SupplierCreatedEvent to kafka for supplier id: {}," +
                    " error: {}", supplierCreatedEvent.getSupplier().getId().getValue(), e.getMessage());
        }
    }

    private BiConsumer<SendResult<String, SupplierAvroModel>, Throwable>
    getCallback(String topicName, SupplierAvroModel message) {

        return (result, ex) -> {
            if (ex == null) {
                RecordMetadata metadata = result.getRecordMetadata();
                log.info("Received new metadata. Topic: {}; Partition {}; Offset {}; Timestamp {}, at time {}",
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp(),
                        System.nanoTime());
            } else {
                log.error("Error while sending message {} to topic {}", message.toString(), topicName, ex);
            }
        };
    }
}

