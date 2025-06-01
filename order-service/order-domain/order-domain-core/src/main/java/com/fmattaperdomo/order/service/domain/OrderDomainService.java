package com.fmattaperdomo.order.service.domain;

import com.fmattaperdomo.order.service.domain.entity.Order;
import com.fmattaperdomo.order.service.domain.entity.Store;
import com.fmattaperdomo.order.service.domain.event.NotificationOrderPaidEvent;
import com.fmattaperdomo.order.service.domain.event.OrderCancelledEvent;
import com.fmattaperdomo.order.service.domain.event.OrderCreatedEvent;
import com.fmattaperdomo.order.service.domain.event.OrderPaidEvent;

import java.util.List;

public interface OrderDomainService {

    OrderCreatedEvent validateAndInitiateOrder(Order order, Store store);

    OrderPaidEvent payOrder(Order order);

    NotificationOrderPaidEvent notificationPayOrder(Order order);

    void approveOrder(Order order);

    void notificationApproveOrder(Order order);

    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

    void cancelOrder(Order order, List<String> failureMessages);

    void notificationCancelOrder(Order order, List<String> failureMessages);
}
