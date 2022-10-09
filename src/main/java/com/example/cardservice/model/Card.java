package com.example.cardservice.model;

import com.example.cardservice.common.CardConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    private String cardNumber;
    private Date expiryDate;
    private String cardHolderName;
}
