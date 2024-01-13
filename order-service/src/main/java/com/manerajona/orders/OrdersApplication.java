package com.manerajona.orders;

import com.manerajona.common.domain.Order;
import com.manerajona.common.exception.PaymentNotProcessedException;
import com.manerajona.orders.core.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RouterFunctions.route;

@SpringBootApplication
public class OrdersApplication {

    private final OrderService orderService;

    public OrdersApplication(OrderService orderService) {
        this.orderService = orderService;
    }

    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class, args);
    }

    @Bean
    RouterFunction<ServerResponse> orderRouter() {
        return route().POST("/orders/checkout", request -> {
            try {
                Order order = request.body(Order.class);
                return ServerResponse.ok().body(orderService.checkout(order));
            } catch (PaymentNotProcessedException e) {
                return ServerResponse.unprocessableEntity().body("Error processing payment");
            }
        }).build();
    }
}