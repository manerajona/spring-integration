package com.manerajona.orders.core;

import com.manerajona.common.domain.Order;

public interface OrderService {
    Order checkout(Order order);
}
