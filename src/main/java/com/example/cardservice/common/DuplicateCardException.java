package com.example.cardservice.common;

public class DuplicateCardException extends Exception{
    public DuplicateCardException(String msg){
        super(msg);
    }
}
