package com.manerajona.common.domain;

import java.util.Set;
import java.util.UUID;

public record Order(UUID id, Payer payer, Payment payment, Set<PurchaseItem> items, OrderStatus status) {
}