package com.fmattaperdomo.order.service.domain;

import com.fmattaperdomo.order.service.domain.dto.create.CreateOrderCommand;
import com.fmattaperdomo.order.service.domain.entity.Customer;
import com.fmattaperdomo.order.service.domain.entity.Order;
import com.fmattaperdomo.order.service.domain.entity.Store;
import com.fmattaperdomo.order.service.domain.event.NotificationOrderCreatedEvent;
import com.fmattaperdomo.order.service.domain.event.OrderCreatedEvent;
import com.fmattaperdomo.order.service.domain.exception.OrderDomainException;
import com.fmattaperdomo.order.service.domain.mapper.OrderDataMapper;
import com.fmattaperdomo.order.service.domain.ports.output.repository.CustomerRepository;
import com.fmattaperdomo.order.service.domain.ports.output.repository.OrderRepository;
import com.fmattaperdomo.order.service.domain.ports.output.repository.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.fmattaperdomo.domain.DomainConstants.UTC;

@Slf4j
@Component
public class OrderCreateHelper {

    private final OrderDomainService orderDomainService;

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    private final StoreRepository storeRepository;

    private final OrderDataMapper orderDataMapper;


    public OrderCreateHelper(OrderDomainService orderDomainService,
                             OrderRepository orderRepository,
                             CustomerRepository customerRepository,
                             StoreRepository storeRepository,
                             OrderDataMapper orderDataMapper) {
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.storeRepository = storeRepository;
        this.orderDataMapper = orderDataMapper;
    }

    @Transactional
    public OrderCreatedEvent persistOrder(CreateOrderCommand createOrderCommand) {
        checkCustomer(createOrderCommand.getCustomerId());
        
        Store store = checkStore(createOrderCommand);
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, store);
        saveOrder(order);
        log.info("Order is created with id: {}", orderCreatedEvent.getOrder().getId().getValue());
        return orderCreatedEvent;
    }

    private Store checkStore(CreateOrderCommand createOrderCommand) {
        Store store = orderDataMapper.createOrderCommandToStore(createOrderCommand);
        Optional<Store> optionalStore = storeRepository.findStoreInformation(store);
        if (optionalStore.isEmpty()) {
            log.warn("Could not find store with store id: {}", createOrderCommand.getStoreId());
            throw new OrderDomainException("Could not find store with store id: " +
                    createOrderCommand.getStoreId());
        }
        return optionalStore.get();
    }

    private void checkCustomer(UUID customerId) {
        log.info("(A)fmattaperdomo: {} ", customerId);
        Optional<Customer> customer = customerRepository.findCustomer(customerId);
        if (customer.isEmpty()) {
            log.warn("Could not find customer with customer id: {}", customerId);
            throw new OrderDomainException("Could not find customer with customer id: " + customerId);
        }
    }

    private Order saveOrder(Order order) {
        Order orderResult = orderRepository.save(order);
        if (orderResult == null) {
            log.error("Could not save order!");
            throw new OrderDomainException("Could not save order!");
        }
        log.info("Order is saved with id: {}", orderResult.getId().getValue());
        return orderResult;
    }
}

