package com.manerajona.cards.core;

import com.manerajona.common.domain.CardDetails;
import com.manerajona.common.domain.CardValidation;

public interface CardService {

    /**
     * Validates:
     * <ol>
     * <li>Card Number using the Luhn Algorithm. <a href="https://en.wikipedia.org/wiki/Luhn_algorithm">wiki</a></li>
     * <li>Expiration Date checking that it is prior to the current month and year.</li>
     * <li>CVC checking that it is between 0 and 1000. See <a href="https://en.wikipedia.org/wiki/Card_security_code">wiki</a></li>
     * </ol>
     *
     * @param card the card instance of {@link CardDetails}
     * @return if the card is valid {@link CardValidation#isValid()} will be {@code true} , or {@code false} otherwise.
     */
    CardValidation validateCard(CardDetails card);
}