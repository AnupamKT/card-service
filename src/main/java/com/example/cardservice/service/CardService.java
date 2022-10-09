package com.example.cardservice.service;

import com.example.cardservice.common.CardNotFoundException;
import com.example.cardservice.common.CardServiceException;
import com.example.cardservice.common.DuplicateCardException;
import com.example.cardservice.common.InvalidCardException;
import com.example.cardservice.entity.CardDTO;
import com.example.cardservice.model.Card;
import com.example.cardservice.model.Response;
import com.example.cardservice.repository.CardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private ValidateCardIF validateCardIF;
    @Autowired
    private CardRepository cardRepository;

    public Response saveCard(Card card, Long userId) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        int status = 200;
        Object cardResult = null;

        try {
            CardDTO cardDTO = mapper.convertValue(card, CardDTO.class);
            cardDTO.setUserId(userId);
            //validate card details
            validateCardIF.validateAddCard(cardDTO);
            //if card is not present already then save card
            if (findCardByCardNumber(cardDTO.getCardNumber()) == null) {
                cardResult = cardRepository.save(cardDTO);
                //throw appropriate exception when card is already present
            } else {
                String msg = "card already exists with number:" + cardDTO.getCardNumber();
                throw new DuplicateCardException(msg);
            }
        } catch (Exception e) {
            handleCardServiceException(e);
        }
        return Response.builder().status(status).body(cardResult).build();
    }

    public Response deleteCard(String cardNumber, long userId) throws Exception {
        int status = 200;
        Object msg = null;
        try {
            CardDTO card = findCardByCardNumber(cardNumber);
            if (card != null) {
                validateCardIF.validateDeleteCard(card, userId);
                cardRepository.delete(card);
                msg = card;
            } else {
                String errorMsg = "no card found for cardNumber:" + cardNumber;
                throw new CardNotFoundException(errorMsg);
            }
        } catch (Exception e) {
            handleCardServiceException(e);
        }
        return Response.builder().status(status).body(msg).build();
    }

    private void handleCardServiceException(Exception e) throws Exception {
        String msg = null;
        if (e instanceof InvalidCardException) {
            msg = "card details not correct: " + e.getMessage();
            throw new InvalidCardException(msg);
        } else if (e instanceof CardNotFoundException) {
            msg = "card not found " + e.getMessage();
            throw new CardNotFoundException(msg);
        } else if (e instanceof DuplicateCardException) {
            msg = "Invalid Card!! " + e.getMessage();
            throw new DuplicateCardException(msg);
        } else {
            msg = "server error occurred:" + e.getMessage();
            throw new CardServiceException(msg);
        }
    }

    private CardDTO findCardByCardNumber(String cardNumber) throws CardNotFoundException {
        CardDTO cardDTO = null;
        Optional<CardDTO> cardOptional = cardRepository.findByCardNumber(cardNumber);
        if (cardOptional.isPresent()) {
            cardDTO = cardOptional.get();
        }
        return cardDTO;
    }
}
