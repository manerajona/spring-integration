package com.manerajona.cards.core;

import com.manerajona.common.domain.CardDetails;
import com.manerajona.common.domain.CardValidation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
class CardServiceImpl implements CardService {
    static boolean checkCNWithLuhnAlgo(String cardNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    static boolean checkED(int expDate) {
        LocalDate now = LocalDate.now();

        final int expYear = 2000 + expDate % 100;
        if (expYear > now.getYear()) {
            return true;
        }

        final int expMonth = expDate / 100;
        return (expYear == now.getYear() && expMonth > now.getMonthValue());
    }

    static boolean checkCVC(int cvc) {
        return (cvc > 0 && cvc < 1000);
    }

    @Override
    public CardValidation validateCard(CardDetails card) {
        return new CardValidation(
                checkCNWithLuhnAlgo(card.number()) && checkED(card.expDate()) && checkCVC(card.cvc())
        );
    }
}