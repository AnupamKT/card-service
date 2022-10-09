package com.example.cardservice.entity;

import com.example.cardservice.common.CardConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="card_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID cardId;
    private Long userId;
    @Column(unique = true,length = 16)
    private String cardNumber;
    private Date expiryDate;
    private String cardHolderName;
    @Enumerated(EnumType.STRING)
    private CardConstants.CardType cardType;
    private Date createdDate;
    private Date UpdatedDate;
}
