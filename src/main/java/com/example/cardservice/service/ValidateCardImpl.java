package com.example.cardservice.service;

import com.example.cardservice.common.CardConstants;
import com.example.cardservice.common.InvalidCardException;
import com.example.cardservice.entity.CardDTO;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ValidateCardImpl implements ValidateCardIF {

    @Override
    public void validateAddCard(CardDTO card) throws InvalidCardException {
        if (card != null) {
            String cardNumber = card.getCardNumber();
            //card is invalid if card length is not 16
            //card is invalid if card expiry date is before current date
            if (cardNumber.length() != 16) {
                throw new InvalidCardException("Invalid card details!!. card number should be 16 digit long: " + cardNumber);
            }
            if (card.getExpiryDate().before(new Date())) {
                throw new InvalidCardException("Invalid card details!!. card is expired: " + cardNumber);
            }
            int cardNum = Integer.valueOf(cardNumber.substring(0, 2));
            if (cardNum < 30) {
                card.setCardType(CardConstants.CardType.MASTERCARD);
            } else if (cardNum >= 30 && cardNum < 60) {
                card.setCardType(CardConstants.CardType.VISA);
            } else {
                card.setCardType(CardConstants.CardType.RUPAY);
            }
            card.setCreatedDate(new Date());
            card.setUpdatedDate(new Date());
        }
    }

    @Override
    public void validateDeleteCard(CardDTO card, long userId) throws InvalidCardException {
        if (card.getUserId() != userId) {
            throw new InvalidCardException("Invalid card details!! user details and card details are not matching " + card.getCardNumber());
        }
    }
}
