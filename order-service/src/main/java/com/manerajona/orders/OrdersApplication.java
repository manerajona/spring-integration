package com.manerajona.orders;

import static org.springframework.web.servlet.function.RouterFunctions.route;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@SpringBootApplication
public class OrdersApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class, args);
    }

    @Bean
    RouterFunction<ServerResponse> orders() {
        return route()
            .POST("/orders/checkout", OrdersApplication::handleRequest)
            .build();
    }

    private static ServerResponse handleRequest(ServerRequest request) {
        return ServerResponse.accepted().build();
    }
}