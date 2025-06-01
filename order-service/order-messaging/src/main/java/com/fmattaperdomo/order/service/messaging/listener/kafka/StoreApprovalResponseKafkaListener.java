package com.fmattaperdomo.order.service.messaging.listener.kafka;

import com.fmattaperdomo.kafka.consumer.KafkaConsumer;
import com.fmattaperdomo.kafka.order.avro.model.OrderApprovalStatus;
import com.fmattaperdomo.kafka.order.avro.model.StoreApprovalResponseAvroModel;
import com.fmattaperdomo.order.service.domain.entity.Order;
import com.fmattaperdomo.order.service.domain.exception.OrderNotFoundException;
import com.fmattaperdomo.order.service.domain.ports.input.message.listener.storeapproval.StoreApprovalResponseMessageListener;
import com.fmattaperdomo.order.service.messaging.mapper.OrderMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.fmattaperdomo.order.service.domain.entity.Order.FAILURE_MESSAGE_DELIMITER;

@Slf4j
@Component
public class StoreApprovalResponseKafkaListener  implements KafkaConsumer<StoreApprovalResponseAvroModel> {

    private final StoreApprovalResponseMessageListener storeApprovalResponseMessageListener;
    private final OrderMessagingDataMapper orderMessagingDataMapper;

    public StoreApprovalResponseKafkaListener(StoreApprovalResponseMessageListener
                                                           storeApprovalResponseMessageListener,
                                                   OrderMessagingDataMapper orderMessagingDataMapper) {
        this.storeApprovalResponseMessageListener = storeApprovalResponseMessageListener;
        this.orderMessagingDataMapper = orderMessagingDataMapper;
    }

    @Override
    @KafkaListener(id = "${kafka-consumer-config.store-approval-consumer-group-id}",
            topics = "${order-service.store-approval-response-topic-name}")
    public void receive(@Payload List<StoreApprovalResponseAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of store approval responses received with keys {}, partitions {} and offsets {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(storeApprovalResponseAvroModel -> {
            try {
                if (OrderApprovalStatus.APPROVED == storeApprovalResponseAvroModel.getOrderApprovalStatus()) {
                    log.info("Processing approved order for order id: {}",
                            storeApprovalResponseAvroModel.getOrderId());
                    storeApprovalResponseMessageListener.orderApproved(orderMessagingDataMapper
                            .approvalResponseAvroModelToApprovalResponse(storeApprovalResponseAvroModel));
                } else if (OrderApprovalStatus.REJECTED == storeApprovalResponseAvroModel.getOrderApprovalStatus()) {
                    log.info("Processing rejected order for order id: {}, with failure messages: {}",
                            storeApprovalResponseAvroModel.getOrderId(),
                            String.join(FAILURE_MESSAGE_DELIMITER,
                                    storeApprovalResponseAvroModel.getFailureMessages()));
                    storeApprovalResponseMessageListener.orderRejected(orderMessagingDataMapper
                            .approvalResponseAvroModelToApprovalResponse(storeApprovalResponseAvroModel));
                }
            } catch (OptimisticLockingFailureException e) {
                //NO-OP for optimistic lock. This means another thread finished the work, do not throw error to prevent reading the data from kafka again!
                log.error("Caught optimistic locking exception in StoreApprovalResponseKafkaListener for order id: {}",
                        storeApprovalResponseAvroModel.getOrderId());
            } catch (OrderNotFoundException e) {
                //NO-OP for OrderNotFoundException
                log.error("No order found for order id: {}", storeApprovalResponseAvroModel.getOrderId());
            }
        });

    }
}
