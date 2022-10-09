package com.example.cardservice.controller;

import com.example.cardservice.model.Card;
import com.example.cardservice.model.Response;
import com.example.cardservice.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/{userId}/add")
    public ResponseEntity<Response> saveCard(@RequestBody Card card, @PathVariable Long userId) throws Exception {
        Response response = cardService.saveCard(card, userId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{cardNumber}")
    public ResponseEntity<Response> deleteCard(@PathVariable String cardNumber,@RequestHeader long userId) throws Exception {
        Response response = cardService.deleteCard(cardNumber,userId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
