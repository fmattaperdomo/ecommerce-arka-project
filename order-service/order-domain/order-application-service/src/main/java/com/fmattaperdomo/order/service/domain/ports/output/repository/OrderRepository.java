package com.fmattaperdomo.order.service.domain.ports.output.repository;

import com.fmattaperdomo.domain.valueobject.OrderId;
import com.fmattaperdomo.order.service.domain.entity.Order;
import com.fmattaperdomo.order.service.domain.valueobject.TrackingId;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(OrderId orderId);

    Optional<Order> findByTrackingId(TrackingId trackingId);
}
