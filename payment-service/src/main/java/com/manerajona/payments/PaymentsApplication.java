package com.manerajona.payments;

import com.manerajona.common.domain.Payment;
import com.manerajona.common.exception.InvalidCardException;
import com.manerajona.payments.core.PaymentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

import static org.springframework.web.servlet.function.RouterFunctions.route;

@SpringBootApplication
public class PaymentsApplication {

    private final PaymentService paymentService;

    public PaymentsApplication(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public static void main(String[] args) {
        SpringApplication.run(PaymentsApplication.class, args);
    }

    @Bean
    RouterFunction<ServerResponse> paymentRouter() {
        return route().POST("/payments", request -> {

                    Payment payment = request.body(Payment.class);
                    try {
                        UUID guid = paymentService.save(payment);

                        var location = UriComponentsBuilder
                                .fromPath(request.path() + "/{guid}")
                                .buildAndExpand(guid)
                                .toUri();

                        return ServerResponse.created(location).build();
                    } catch (InvalidCardException e) {
                        return ServerResponse.badRequest().body("Invalid Card");
                    }
                })
                .GET("/payments/{guid}", request -> {
                    UUID guid = UUID.fromString(request.pathVariable("guid"));
                    return paymentService.getByGuid(guid)
                            .map(payment -> ServerResponse.ok().body(payment))
                            .orElse(ServerResponse.notFound().build());
                })
                .build();
    }
}
