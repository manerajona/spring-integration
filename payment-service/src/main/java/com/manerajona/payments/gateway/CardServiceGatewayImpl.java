package com.manerajona.payments.gateway;

import com.manerajona.common.domain.CardDetails;
import com.manerajona.common.domain.CardValidation;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
class CardServiceGatewayImpl implements CardServiceGateway {

    private final WebClient client;

    CardServiceGatewayImpl() {
        this.client = WebClient.create("http://localhost:8587");
    }

    @Override
    public Mono<CardValidation> validate(CardDetails cardDetails) {
        return client.post()
                .uri("/cards/validate")
                .body(Mono.just(cardDetails), CardDetails.class)
                .retrieve()
                .bodyToMono(CardValidation.class);
    }
}