package com.fmattaperdomo.order.service.domain;

import com.fmattaperdomo.order.service.domain.entity.Order;
import com.fmattaperdomo.order.service.domain.entity.ProductStore;
import com.fmattaperdomo.order.service.domain.entity.Store;
import com.fmattaperdomo.order.service.domain.event.NotificationOrderPaidEvent;
import com.fmattaperdomo.order.service.domain.event.OrderCancelledEvent;
import com.fmattaperdomo.order.service.domain.event.OrderCreatedEvent;
import com.fmattaperdomo.order.service.domain.event.OrderPaidEvent;
import com.fmattaperdomo.order.service.domain.exception.OrderDomainException;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static com.fmattaperdomo.domain.DomainConstants.UTC;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Store store) {
        validateStore(store);
        setOrderProductInformation(order, store);
        order.validateOrder();
        order.initializeOrder();
        log.info("Order with id: {} is initiated", order.getId().getValue());
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        log.info("Order with id: {} is paid", order.getId().getValue());
        return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public NotificationOrderPaidEvent notificationPayOrder(Order order) {
        log.info("(Notification) Order with id: {} is paid", order.getId().getValue());
        return new NotificationOrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));

    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
        log.info("Order with id: {} is approved", order.getId().getValue());
    }

    @Override
    public void notificationApproveOrder(Order order) {
        log.info("(Notification) Order with id: {} is approved", order.getId().getValue());
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        log.info("Order payment is cancelling for order id: {}", order.getId().getValue());
        return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
        log.info("Order with id: {} is cancelled", order.getId().getValue());
    }

    @Override
    public void notificationCancelOrder(Order order, List<String> failureMessages) {
        log.info("(Notification) Order with id: {} is cancelled", order.getId().getValue());
    }

    private void validateStore(Store store) {
        if (!store.isActive()) {
            throw new OrderDomainException("Store with id " + store.getId().getValue() +
                    " is currently not active!");
        }
    }

    private void setOrderProductInformation(Order order, Store store) {
        order.getItems().forEach(orderItem -> store.getProductsStore().forEach(productStore -> {
            ProductStore currentProductStore = orderItem.getProductStore();
            if (currentProductStore.equals(productStore)) {
                currentProductStore.updateWithConfirmedProductAndPrice(productStore.getStoreId(),productStore.getProduct(),
                        productStore.getPrice(), productStore.getStockQuantity(), productStore.getBarcode(), productStore.getSupplierId());
            }
        }));
    }
}

