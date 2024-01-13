package com.manerajona.payments.core;

import com.manerajona.common.domain.CardValidation;
import com.manerajona.common.domain.Payment;
import com.manerajona.common.exception.InvalidCardException;
import com.manerajona.payments.gateway.CardServiceGateway;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
class PaymentServiceImpl implements PaymentService {

    private final ConcurrentMap<UUID, Payment> payments = new ConcurrentHashMap<>();

    private final CardServiceGateway cardServiceGateway;

    PaymentServiceImpl(CardServiceGateway cardServiceGateway) {
        this.cardServiceGateway = cardServiceGateway;
    }

    @Override
    public UUID save(Payment payment) throws InvalidCardException {
        CardValidation cardValidation = cardServiceGateway.validate(payment.card()).block();
        if (cardValidation.isNotValid()) {
            throw new InvalidCardException();
        }
        UUID guid = UUID.randomUUID();
        payments.put(guid, payment);
        return guid;
    }

    @Override
    public Optional<Payment> getByGuid(UUID guid) {
        return Optional.ofNullable(payments.get(guid));
    }

}