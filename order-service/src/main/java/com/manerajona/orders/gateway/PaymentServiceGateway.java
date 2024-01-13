package com.manerajona.orders.gateway;

import com.manerajona.common.domain.Payment;
import reactor.core.publisher.Mono;

public interface PaymentServiceGateway {
    Mono<String> makePayment(Payment payment);
}
