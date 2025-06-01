package com.fmattaperdomo.store.service.messaging.listener.kafka;

import com.fmattaperdomo.kafka.consumer.KafkaConsumer;
import com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel;
import com.fmattaperdomo.store.service.domain.exception.StoreApplicationServiceException;
import com.fmattaperdomo.store.service.domain.exception.StoreNotFoundException;
import com.fmattaperdomo.store.service.domain.ports.input.message.listener.StoreApprovalRequestMessageListener;
import com.fmattaperdomo.store.service.messaging.mapper.StoreMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLState;
import org.springframework.dao.DataAccessException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Component
public class StoreApprovalRequestKafkaListener  implements KafkaConsumer<StoreApprovalRequestAvroModel>  {

    private final StoreApprovalRequestMessageListener storeApprovalRequestMessageListener;
    private final StoreMessagingDataMapper storeMessagingDataMapper;

    public StoreApprovalRequestKafkaListener(StoreApprovalRequestMessageListener
                                                          storeApprovalRequestMessageListener,
                                                  StoreMessagingDataMapper
                                                          storeMessagingDataMapper) {
        this.storeApprovalRequestMessageListener = storeApprovalRequestMessageListener;
        this.storeMessagingDataMapper = storeMessagingDataMapper;
    }

    @Override
    @KafkaListener(id = "${kafka-consumer-config.store-approval-consumer-group-id}",
            topics = "${store-service.store-approval-request-topic-name}")
    public void receive(@Payload List<StoreApprovalRequestAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of orders approval requests received with keys {}, partitions {} and offsets {}" +
                        ", sending for store approval",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(storeApprovalRequestAvroModel -> {
            try {
                log.info("Processing order approval for order id: {}", storeApprovalRequestAvroModel.getOrderId());
                storeApprovalRequestMessageListener.approveOrder(storeMessagingDataMapper.
                        storeApprovalRequestAvroModelToStoreApproval(storeApprovalRequestAvroModel));
            } catch (DataAccessException e) {
                SQLException sqlException = (SQLException) e.getRootCause();
                if (sqlException != null && sqlException.getSQLState() != null &&
                        PSQLState.UNIQUE_VIOLATION.getState().equals(sqlException.getSQLState())) {
                    //NO-OP for unique constraint exception
                    log.error("Caught unique constraint exception with sql state: {} " +
                                    "in StoreApprovalRequestKafkaListener for order id: {}",
                            sqlException.getSQLState(), storeApprovalRequestAvroModel.getOrderId());
                } else {
                    throw new StoreApplicationServiceException("Throwing DataAccessException in" +
                            " StoreApprovalRequestKafkaListener: " + e.getMessage(), e);
                }
            } catch (StoreNotFoundException e) {
                //NO-OP for StoreNotFoundException
                log.error("No store found for store id: {}, and order id: {}",
                        storeApprovalRequestAvroModel.getStoreId(),
                        storeApprovalRequestAvroModel.getOrderId());
            }
        });
    }

}

