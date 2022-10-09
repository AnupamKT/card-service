package com.example.cardservice.common;

public class CardNotFoundException extends Exception{
    public CardNotFoundException(String msg){
        super(msg);
    }
}
