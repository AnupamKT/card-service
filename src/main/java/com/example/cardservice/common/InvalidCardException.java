package com.example.cardservice.common;

public class InvalidCardException extends Exception {
    public InvalidCardException(String msg) {
        super(msg);
    }
}
