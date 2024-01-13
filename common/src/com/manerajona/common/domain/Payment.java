package com.manerajona.common.domain;

public record Payment(Double amount, PaymentMethod method, CardDetails card) {
}