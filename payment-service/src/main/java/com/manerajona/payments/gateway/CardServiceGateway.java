package com.manerajona.payments.gateway;

import com.manerajona.common.domain.CardDetails;
import com.manerajona.common.domain.CardValidation;
import reactor.core.publisher.Mono;

public interface CardServiceGateway {

    Mono<CardValidation> validate(CardDetails cardDetails);
}
