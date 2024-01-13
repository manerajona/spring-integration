package com.manerajona.orders.gateway;

import com.manerajona.common.domain.Payment;
import com.manerajona.common.exception.PaymentNotProcessedException;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
class PaymentServiceGatewayImpl implements PaymentServiceGateway {

    private final WebClient client;

    PaymentServiceGatewayImpl() {
        this.client = WebClient.create("http://localhost:8586");
    }

    @Override
    public Mono<String> makePayment(Payment payment) throws PaymentNotProcessedException {
        return client.post()
                .uri("/payments")
                .body(Mono.just(payment), Payment.class)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new PaymentNotProcessedException()))
                .toBodilessEntity()
                .map(response -> response.getHeaders().getLocation().getPath());
    }
}
