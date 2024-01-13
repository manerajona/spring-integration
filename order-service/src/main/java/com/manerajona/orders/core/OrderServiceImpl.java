package com.manerajona.orders.core;

import com.manerajona.common.domain.Order;
import com.manerajona.common.domain.OrderStatus;
import com.manerajona.common.exception.PaymentNotProcessedException;
import com.manerajona.orders.gateway.PaymentServiceGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final PaymentServiceGateway paymentServiceGateway;

    OrderServiceImpl(PaymentServiceGateway paymentServiceGateway) {
        this.paymentServiceGateway = paymentServiceGateway;
    }

    @Override
    public Order checkout(Order order) throws PaymentNotProcessedException {
        String location = paymentServiceGateway.makePayment(order.payment()).block();
        log.info("payment created [{}]", location);
        return new Order(UUID.randomUUID(), order.payer(), order.payment(), order.items(), OrderStatus.APPROVED);
    }
}