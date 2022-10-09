package com.example.cardservice.repository;

import com.example.cardservice.entity.CardDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<CardDTO, UUID> {
    Optional<CardDTO> findByCardNumber(String cardNumber);
}
