package com.example.cardservice.service;

import com.example.cardservice.common.InvalidCardException;
import com.example.cardservice.entity.CardDTO;
import com.example.cardservice.model.Card;
import org.springframework.stereotype.Service;

@Service
public interface ValidateCardIF {

    void validateAddCard(CardDTO card) throws InvalidCardException;
    void validateDeleteCard(CardDTO card,long userId) throws InvalidCardException;

}
