package com.fmattaperdomo.order.service.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fmattaperdomo.domain.valueobject.*;
import com.fmattaperdomo.order.service.domain.dto.create.CreateOrderCommand;
import com.fmattaperdomo.order.service.domain.dto.create.CreateOrderResponse;
import com.fmattaperdomo.order.service.domain.dto.create.OrderAddress;
import com.fmattaperdomo.order.service.domain.dto.create.OrderItem;
import com.fmattaperdomo.order.service.domain.entity.*;
import com.fmattaperdomo.order.service.domain.exception.OrderDomainException;
import com.fmattaperdomo.order.service.domain.mapper.OrderDataMapper;
import com.fmattaperdomo.order.service.domain.outbox.model.payment.OrderPaymentEventPayload;
import com.fmattaperdomo.order.service.domain.outbox.model.payment.OrderPaymentOutboxMessage;
import com.fmattaperdomo.order.service.domain.ports.input.service.OrderApplicationService;
import com.fmattaperdomo.order.service.domain.ports.output.repository.CustomerRepository;
import com.fmattaperdomo.order.service.domain.ports.output.repository.OrderRepository;
import com.fmattaperdomo.order.service.domain.ports.output.repository.PaymentOutboxRepository;
import com.fmattaperdomo.order.service.domain.ports.output.repository.StoreRepository;
import com.fmattaperdomo.outbox.OutboxStatus;
import com.fmattaperdomo.saga.SagaStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.fmattaperdomo.saga.order.SagaConstants.ORDER_SAGA_NAME;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = OrderTestConfiguration.class)
public class OrderApplicationServiceTest {

    @Autowired
    private OrderApplicationService orderApplicationService;

    @Autowired
    private OrderDataMapper orderDataMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private PaymentOutboxRepository paymentOutboxRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private CreateOrderCommand createOrderCommand;
    private CreateOrderCommand createOrderCommandWrongPrice;
    private CreateOrderCommand createOrderCommandWrongProductPrice;
    private final UUID CUSTOMER_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb41");
    private final UUID PRODUCTSTORE_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb42");
    private final UUID PRODUCT_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb43");
    private final UUID ORDER_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb44");
    private final UUID SAGA_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb45");
    private final BigDecimal PRICE = new BigDecimal("200.00");
    private final UUID ADDRESS_ID  = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb46");
    private final UUID CONTACT_ID  = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb47");
    private final UUID STORE_ID  = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb48");
    private final UUID SUPPLIER_ID  = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb49");
    private final UUID BRAND_ID  = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb50");
    private final UUID CATEGORY_ID  = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb51");
    private final Long PRODUCTFEATURE_ID  = 1L;
    private final Long FEATURE_ID  = 1L;
//
//    @BeforeAll
//    public void init() {
//        createOrderCommand = CreateOrderCommand.builder()
//                .customerId(CUSTOMER_ID)
//                .storeId(STORE_ID)
//                .address(OrderAddress.builder()
//                        .street("street_1")
//                        .city("Marsella")
//                        .state("Bocas del Ródano")
//                        .country("French")
//                        .zipCode("1000AB")
//                        .build())
//                .totalAmount(PRICE)
//                .items(List.of(OrderItem.builder()
//                                    .productStoreId(PRODUCTSTORE_ID)
//                                    .quantity(1)
//                                    .price(new BigDecimal("50.00"))
//                                    .subTotal(new BigDecimal("150.00"))
//                                    .build(),
//                        OrderItem.builder()
//                                .productStoreId(PRODUCTSTORE_ID)
//                                .quantity(3)
//                                .price(new BigDecimal("50.00"))
//                                .subTotal(new BigDecimal("150.00"))
//                                .build()))
//                .build();
//
//        createOrderCommandWrongPrice = CreateOrderCommand.builder()
//                .customerId(CUSTOMER_ID)
//                .storeId(PRODUCTSTORE_ID)
//                .address(OrderAddress.builder()
//                        .street("street_1")
//                        .city("Marsella")
//                        .state("Bocas del Ródano")
//                        .country("French")
//                        .zipCode("1000AB")
//                        .build())
//                .totalAmount(new BigDecimal("250.00"))
//                .items(List.of(OrderItem.builder()
//                                .productStoreId(PRODUCTSTORE_ID)
//                                .quantity(1)
//                                .price(new BigDecimal("50.00"))
//                                .subTotal(new BigDecimal("150.00"))
//                                .build(),
//                        OrderItem.builder()
//                                .productStoreId(PRODUCTSTORE_ID)
//                                .quantity(3)
//                                .price(new BigDecimal("50.00"))
//                                .subTotal(new BigDecimal("150.00"))
//                                .build()))
//                .build();
//
//        createOrderCommandWrongProductPrice = CreateOrderCommand.builder()
//                .customerId(CUSTOMER_ID)
//                .storeId(PRODUCTSTORE_ID)
//                .address(OrderAddress.builder()
//                        .street("street_1")
//                        .city("Marsella")
//                        .state("Bocas del Ródano")
//                        .country("French")
//                        .zipCode("1000AB")
//                        .build())
//                .totalAmount(new BigDecimal("210.00"))
//                .items(List.of(OrderItem.builder()
//                                .productStoreId(PRODUCTSTORE_ID)
//                                .quantity(1)
//                                .price(new BigDecimal("60.00"))
//                                .subTotal(new BigDecimal("60.00"))
//                                .build(),
//                        OrderItem.builder()
//                                .productStoreId(PRODUCTSTORE_ID)
//                                .quantity(3)
//                                .price(new BigDecimal("50.00"))
//                                .subTotal(new BigDecimal("150.00"))
//                                .build()))
//                .build();
//
//        Customer customer = new Customer(new CustomerId(CUSTOMER_ID));
//
//        Store storeResponse = Store.builder()
//                .storeId(new StoreId(createOrderCommand.getStoreId()))
//                .name("Store # 1")
//                .description("Description # 1")
//                .storeAddress(new Address(ADDRESS_ID, "Calle 1","Bogotá","Bogotá","Colombia","111111"))
//                .storeContact(new Contact(CONTACT_ID,"CONTACT # 1","PHONE # 1"))
//                .active(true)
//                .productsStore(List.of(
//                        ProductStore.builder()
//                                .productStoreId(new ProductStoreId(PRODUCTSTORE_ID))
//                                .storeId(new StoreId(STORE_ID))
//                                .product(
//                                        Product.builder()
//                                                .productId(new ProductId(PRODUCT_ID))
//                                                .name("Name 1")
//                                                .description("Description 1")
//                                                .brand(new Brand(BRAND_ID, "brand # 1"))
//                                                .category(new Category(CATEGORY_ID, "category # 1"))
//                                                .imageUrl("https://localhost/images/image_1")
//                                                .productFeatures(List.of(
//                                                        ProductFeature.builder()
//                                                                .productFeatureId(new ProductFeatureId(PRODUCTFEATURE_ID))
//                                                                .productId(new ProductId(PRODUCT_ID))
//                                                                .feature(new Feature(FEATURE_ID,"feature # 1"))
//                                                                .value("valor # 1")
//                                                                .build(),
//                                                        ProductFeature.builder()
//                                                                .productFeatureId(new ProductFeatureId(PRODUCTFEATURE_ID))
//                                                                .productId(new ProductId(PRODUCT_ID))
//                                                                .feature(new Feature(FEATURE_ID,"feature # 1"))
//                                                                .value("valor # 1")
//                                                                .build()))
//                                                .active(Boolean.TRUE)
//                                        .build())
//                                .price(new Money(new BigDecimal("50.00")))
//                                .stockQuantity(10)
//                                .barcode("123")
//                                .supplierId(new SupplierId(SUPPLIER_ID))
//                                .build(),
//                                ProductStore.builder()
//                                        .productStoreId(new ProductStoreId(PRODUCTSTORE_ID))
//                                        .storeId(new StoreId(STORE_ID))
//                                        .product(
//                                                Product.builder()
//                                                        .productId(new ProductId(PRODUCT_ID))
//                                                        .name("Name 1")
//                                                        .description("Description 1")
//                                                        .brand(new Brand(BRAND_ID, "brand # 1"))
//                                                        .category(new Category(CATEGORY_ID, "category # 1"))
//                                                        .imageUrl("https://localhost/images/image_1")
//                                                        .productFeatures(List.of(
//                                                                ProductFeature.builder()
//                                                                        .productFeatureId(new ProductFeatureId(PRODUCTFEATURE_ID))
//                                                                        .productId(new ProductId(PRODUCT_ID))
//                                                                        .feature(new Feature(FEATURE_ID,"feature # 1"))
//                                                                        .value("valor # 1")
//                                                                        .build(),
//                                                                ProductFeature.builder()
//                                                                        .productFeatureId(new ProductFeatureId(PRODUCTFEATURE_ID))
//                                                                        .productId(new ProductId(PRODUCT_ID))
//                                                                        .feature(new Feature(FEATURE_ID,"feature # 1"))
//                                                                        .value("valor # 1")
//                                                                        .build()))
//                                                        .active(Boolean.TRUE)
//                                                        .build())
//                                        .price(new Money(new BigDecimal("50.00")))
//                                        .stockQuantity(10)
//                                        .barcode("123")
//                                        .supplierId(new SupplierId(SUPPLIER_ID))
//                                        .build()))
//                .build();
//
//        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
//        order.setId(new OrderId(ORDER_ID));
//
//        when(customerRepository.findCustomer(CUSTOMER_ID)).thenReturn(Optional.of(customer));
//        when(storeRepository.findStoreInformation(orderDataMapper.createOrderCommandToStore(createOrderCommand)))
//                .thenReturn(Optional.of(storeResponse));
//        when(orderRepository.save(any(Order.class))).thenReturn(order);
//        when(paymentOutboxRepository.save(any(OrderPaymentOutboxMessage.class))).thenReturn(getOrderPaymentOutboxMessage());
//    }
//
//    @Test
//    public void testCreateOrder() {
//        CreateOrderResponse createOrderResponse = orderApplicationService.createOrder(createOrderCommand);
//        assertEquals(OrderStatus.PENDING, createOrderResponse.getOrderStatus());
//        assertEquals("Order created successfully", createOrderResponse.getMessage());
//        assertNotNull(createOrderResponse.getOrderTrackingId());
//    }
//
//    @Test
//    public void testCreateOrderWithWrongTotalPrice() {
//        OrderDomainException orderDomainException = assertThrows(OrderDomainException.class,
//                () -> orderApplicationService.createOrder(createOrderCommandWrongPrice));
//        assertEquals("Total price: 250.00 is not equal to Order items total: 200.00!", orderDomainException.getMessage());
//    }
//
//    @Test
//    public void testCreateOrderWithWrongProductPrice() {
//        OrderDomainException orderDomainException = assertThrows(OrderDomainException.class,
//                () -> orderApplicationService.createOrder(createOrderCommandWrongProductPrice));
//        assertEquals("Order item price: 60.00 is not valid for product " + PRODUCT_ID, orderDomainException.getMessage());
//    }
//
//    @Test
//    public void testCreateOrderWithPassiveStore() {
//        Store storeResponse = Store.builder()
//                .storeId(new StoreId(createOrderCommand.getStoreId()))
//                .name("Store # 1")
//                .description("Description # 1")
//                .storeAddress(new Address(ADDRESS_ID, "Calle 1","Bogotá","Bogotá","Colombia","111111"))
//                .storeContact(new Contact(CONTACT_ID,"CONTACT # 1","PHONE # 1"))
//                .active(true)
//                .productsStore(List.of(
//                        ProductStore.builder()
//                                .productStoreId(new ProductStoreId(PRODUCTSTORE_ID))
//                                .storeId(new StoreId(STORE_ID))
//                                .product(
//                                        Product.builder()
//                                                .productId(new ProductId(PRODUCT_ID))
//                                                .name("Name 1")
//                                                .description("Description 1")
//                                                .brand(new Brand(BRAND_ID, "brand # 1"))
//                                                .category(new Category(CATEGORY_ID, "category # 1"))
//                                                .imageUrl("https://localhost/images/image_1")
//                                                .productFeatures(List.of(
//                                                        ProductFeature.builder()
//                                                                .productFeatureId(new ProductFeatureId(PRODUCTFEATURE_ID))
//                                                                .productId(new ProductId(PRODUCT_ID))
//                                                                .feature(new Feature(FEATURE_ID,"feature # 1"))
//                                                                .value("valor # 1")
//                                                                .build(),
//                                                        ProductFeature.builder()
//                                                                .productFeatureId(new ProductFeatureId(PRODUCTFEATURE_ID))
//                                                                .productId(new ProductId(PRODUCT_ID))
//                                                                .feature(new Feature(FEATURE_ID,"feature # 1"))
//                                                                .value("valor # 1")
//                                                                .build()))
//                                                .active(Boolean.TRUE)
//                                                .build())
//                                .price(new Money(new BigDecimal("50.00")))
//                                .stockQuantity(10)
//                                .barcode("123")
//                                .supplierId(new SupplierId(SUPPLIER_ID))
//                                .build(),
//                        ProductStore.builder()
//                                .productStoreId(new ProductStoreId(PRODUCTSTORE_ID))
//                                .storeId(new StoreId(STORE_ID))
//                                .product(
//                                        Product.builder()
//                                                .productId(new ProductId(PRODUCT_ID))
//                                                .name("Name 1")
//                                                .description("Description 1")
//                                                .brand(new Brand(BRAND_ID, "brand # 1"))
//                                                .category(new Category(CATEGORY_ID, "category # 1"))
//                                                .imageUrl("https://localhost/images/image_1")
//                                                .productFeatures(List.of(
//                                                        ProductFeature.builder()
//                                                                .productFeatureId(new ProductFeatureId(PRODUCTFEATURE_ID))
//                                                                .productId(new ProductId(PRODUCT_ID))
//                                                                .feature(new Feature(FEATURE_ID,"feature # 1"))
//                                                                .value("valor # 1")
//                                                                .build(),
//                                                        ProductFeature.builder()
//                                                                .productFeatureId(new ProductFeatureId(PRODUCTFEATURE_ID))
//                                                                .productId(new ProductId(PRODUCT_ID))
//                                                                .feature(new Feature(FEATURE_ID,"feature # 1"))
//                                                                .value("valor # 1")
//                                                                .build()))
//                                                .active(Boolean.TRUE)
//                                                .build())
//                                .price(new Money(new BigDecimal("50.00")))
//                                .stockQuantity(10)
//                                .barcode("123")
//                                .supplierId(new SupplierId(SUPPLIER_ID))
//                                .build()))
//                .build();
//
//        when(storeRepository.findStoreInformation(orderDataMapper.createOrderCommandToStore(createOrderCommand)))
//                .thenReturn(Optional.of(storeResponse));
//        OrderDomainException orderDomainException = assertThrows(OrderDomainException.class,
//                () -> orderApplicationService.createOrder(createOrderCommand));
//        assertEquals("Store with id " + STORE_ID + " is currently not active!", orderDomainException.getMessage());
//    }
//
//    private OrderPaymentOutboxMessage getOrderPaymentOutboxMessage() {
//        OrderPaymentEventPayload orderPaymentEventPayload = OrderPaymentEventPayload.builder()
//                .orderId(ORDER_ID.toString())
//                .customerId(CUSTOMER_ID.toString())
//                .price(PRICE)
//                .createdAt(ZonedDateTime.now())
//                .paymentOrderStatus(PaymentOrderStatus.PENDING.name())
//                .build();
//
//        return OrderPaymentOutboxMessage.builder()
//                .id(UUID.randomUUID())
//                .sagaId(SAGA_ID)
//                .createdAt(ZonedDateTime.now())
//                .type(ORDER_SAGA_NAME)
//                .payload(createPayload(orderPaymentEventPayload))
//                .orderStatus(OrderStatus.PENDING)
//                .sagaStatus(SagaStatus.STARTED)
//                .outboxStatus(OutboxStatus.STARTED)
//                .version(0)
//                .build();
//    }
//
//    private String createPayload(OrderPaymentEventPayload orderPaymentEventPayload) {
//        try {
//            return objectMapper.writeValueAsString(orderPaymentEventPayload);
//        } catch (JsonProcessingException e) {
//            throw new OrderDomainException("Cannot create OrderPaymentEventPayload object!");
//        }
//    }

}
