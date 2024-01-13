package com.manerajona.payments.core;

import com.manerajona.common.domain.Payment;

import java.util.Optional;
import java.util.UUID;

public interface PaymentService {

    /**
     * Save payment.
     *
     * @param payment the payment instance of {@link Payment}
     * @return the payment id instance of {@link UUID}
     */
    UUID save(Payment payment);

    Optional<Payment> getByGuid(UUID guid);
}