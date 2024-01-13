package com.manerajona.cards;

import com.manerajona.cards.core.CardService;
import com.manerajona.common.domain.CardDetails;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RouterFunctions.route;

@SpringBootApplication
public class CardsApplication {

    private final CardService cardService;

    public CardsApplication(CardService cardService) {
        this.cardService = cardService;
    }

    public static void main(String[] args) {
        SpringApplication.run(CardsApplication.class, args);
    }

    @Bean
    RouterFunction<ServerResponse> cardRouter() {
        return route().POST("/cards/validate", request -> {
            CardDetails cardDetails = request.body(CardDetails.class);
            return ServerResponse.ok().body(cardService.validateCard(cardDetails));
        }).build();
    }
}
